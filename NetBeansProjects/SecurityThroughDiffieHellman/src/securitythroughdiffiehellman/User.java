/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package securitythroughdiffiehellman;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 *
 * @author shadab
 */
public class User {
    private BigInteger privateKey;
    
    private BigInteger publicKey;
     
    private BigInteger sharedSecret;
      
    private BigInteger prime;
       
    private BigInteger generator;
    
    // constructor to set prime and generator
    public User(BigInteger p,BigInteger g){
        this.prime=p;
        this.generator=g;
    }
    
    // Generate private key and Calculate Public key
    public void generatePublicKey(){
        SecureRandom random=new SecureRandom();
        
        // Generate a random private key (a or b) between 2 and prime-1
        this.privateKey=new BigInteger(5,random);
        
        // publickey=generator^privatekey mod prime
        this.publicKey=generator.modPow(privateKey, prime);
        
        System.out.println("Generated Private Key: " + privateKey);
        System.out.println("Calculated Public Key: " + publicKey);      
    }
    
    // calculate sharedSecret using other's public key
    public void calculateSharedSecret(BigInteger otherPublicKey){
        this.sharedSecret=otherPublicKey.modPow(privateKey, prime);
    }
    
    // Get public key
    public BigInteger getPublicKey(){
        return this.publicKey;
    }
    
    // Get privaetKey
    public BigInteger getPrivateKey(){
        return this.privateKey;
    }
    
    //  Get sharedSecret
    public BigInteger getSharedSecret(){
        return this.sharedSecret;
    }
    
}
