/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securitythroughdiffiehellman;
import java.math.BigInteger;
import java.util.Scanner;


/**
 *
 * @author shadab
 */
public class DiffieHellmanDemo {
    public static void main(String[] args){
       System.out.println("=== DAY 1: Diffie-Hellman Key Exchange Test ===\n");
       System.out.println("🔒 Testing different prime sizes for security\n");
       Scanner scanner=new Scanner(System.in); 
        // Let user choose prime size
        System.out.println("Choose prime size for testing:");
        System.out.println("1 - Small (8-bit) - WEAK SECURITY");
        System.out.println("2 - Medium (16-bit) - BASIC SECURITY"); 
        System.out.println("3 - Large (64-bit) - STRONG SECURITY");
        System.out.print("Enter choice (1-3): ");
        
        int choice = scanner.nextInt();
        BigInteger prime;
        String securityLevel;
        
        switch(choice) {
            case 1:
                prime = new BigInteger("251"); // Small 8-bit prime
                securityLevel = "WEAK";
                break;
            case 2:
                prime = new BigInteger("65521"); // Medium 16-bit prime  
                securityLevel = "BASIC";
                break;
            case 3:
                prime = new BigInteger("170141183460469231731687303715884105727"); // Large prime
                securityLevel = "STRONG";
                break;
            default:
                prime = new BigInteger("23");
                securityLevel = "DEMO";
        }
        
        BigInteger generator = new BigInteger("5");
        
        System.out.println("\n=== TESTING " + securityLevel + " SECURITY ===");
        System.out.println("Prime size: " + prime.bitLength() + " bits");
        System.out.println();

        // Create participants
        User alice = new User(prime, generator);
        User bob = new User(prime, generator);
        Eavesdropper mallory = new Eavesdropper("Mallory the Hacker");

        // Key generation and exchange
        alice.generatePublicKey();
        bob.generatePublicKey();
        mallory.interceptPublicInfo(prime, generator, alice.getPublicKey(), bob.getPublicKey());

        // Legitimate exchange
        alice.calculateSharedSecret(bob.getPublicKey());
        bob.calculateSharedSecret(alice.getPublicKey());

        // Attack attempt
        mallory.attemptToBreakKey();

        // Results
        System.out.println("\n=== FINAL RESULTS ===");
        System.out.println("Alice and Bob have the same secret: " + 
            alice.getSharedSecret().equals(bob.getSharedSecret()));
        
        mallory.reportResults(alice.getSharedSecret());
        
        scanner.close();
    }
}
