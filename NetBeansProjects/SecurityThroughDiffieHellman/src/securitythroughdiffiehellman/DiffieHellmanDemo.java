/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securitythroughdiffiehellman;
import java.math.BigInteger;


/**
 *
 * @author shadab
 */
public class DiffieHellmanDemo {
    public static void main(String[] args){
        System.out.println("=== DAY 1: Diffie-Hellman Key Exchange Test ===\n");
        
        // Step 1: Setup - Choose a SMALL prime and generator for testing
        // Using small numbers makes it easier to see what's happening
        BigInteger prime = new BigInteger("23");    // A small prime number
        BigInteger generator = new BigInteger("5"); // A generator for this prime
        
        System.out.println("Public Parameters (Everyone knows these):");
        System.out.println("Prime (p): " + prime);
        System.out.println("Generator (g): " + generator);
        System.out.println();
        
        
        User alice=new User(prime,generator);
        User bob=new User(prime,generator);
        
        System.out.println("=== ALICE'S SIDE ===");
        alice.generatePublicKey();
        System.out.println();
        
        
        System.out.println("=== BOB'S SIDE ===");
        bob.generatePublicKey();
        System.out.println();
        
        
        System.out.println("=== KEY EXCHANGE ===");
        System.out.println("Alice sends her public key to Bob");
        System.out.println("Bob sends his public key to Alice");
        System.out.println();
        
        alice.calculateSharedSecret(bob.getPublicKey());
        bob.calculateSharedSecret(alice.getPublicKey());
        
        System.out.println("=== RESULTS ===");
        System.out.println("Alice's Shared Secret: " + alice.getSharedSecret());
        System.out.println("Bob's Shared Secret: " + bob.getSharedSecret());
        System.out.println();
        
        // Step 5: Verify they are the same
        if (alice.getSharedSecret().equals(bob.getSharedSecret())) {
            System.out.println("✅ SUCCESS! Alice and Bob have the same shared secret!");
            System.out.println("The key exchange worked perfectly!");
        } else {
            System.out.println("❌ FAILED! Shared secrets don't match.");
            System.out.println("Check your calculations.");
        }
    }
    
}
