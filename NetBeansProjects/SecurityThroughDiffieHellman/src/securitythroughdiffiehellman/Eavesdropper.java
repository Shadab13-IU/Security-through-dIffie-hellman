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
public class Eavesdropper {
    private String name;
    
    private BigInteger stolenPrime;
    
    private BigInteger stolenGenerator;
    
    private BigInteger stolenPublicKeyA;
    
    private BigInteger stolenPublicKeyB;
    
    private BigInteger calculateSecret;
    
    public Eavesdropper(String name){
        this.name=name;
    }
    
    // the Eavesdropper steals the public information
    public void interceptsPublicInfo(BigInteger p,BigInteger g,BigInteger publicKeyA, BigInteger publicKeyB){
        this.stolenPrime=p;
        this.stolenGenerator=g;
        this.stolenPublicKeyA=publicKeyA;
        this.stolenPublicKeyB=publicKeyB;
        
        System.out.println("🔍 " + name + " intercepted public information!");
        System.out.println("   Prime (p): " + p);
        System.out.println("   Generator (g): " + g);
        System.out.println("   Alice's Public Key: " + publicKeyA);
        System.out.println("   Bob's Public Key: " + publicKeyB);
    }
    
    
    
}
