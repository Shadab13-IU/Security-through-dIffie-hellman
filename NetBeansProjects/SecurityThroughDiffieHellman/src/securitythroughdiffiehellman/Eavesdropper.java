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
    
    private BigInteger calculatedSecret;
    
    boolean attackSuccessful;
    
    public Eavesdropper(String name){
        this.name=name;
    }
    
    // the Eavesdropper steals the public information
    public void interceptPublicInfo(BigInteger p,BigInteger g,BigInteger publicKeyA, BigInteger publicKeyB){
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
    
    public void attemptToBreakKey(){
        System.out.println("\n"+name+"is trying to break secret key...");
        int primeSize=stolenPrime.bitLength();
        System.out.println("Prime Size"+primeSize+"bits");
        
        if(primeSize<=16){
            System.out.println("Small prime-attempting Brute force attack...");
            bruteForceAttack();
        }else if(primeSize<=32){
            System.out.println("Medium prime-Brute force attack take hours/days...");
            demonstrateImpracticalAttack();
        }else{
            System.out.println("   🔒 Large prime - COMPUTATIONALLY SECURE");
            System.out.println("   ❌ Brute force would take BILLIONS OF YEARS");
        }
    }
        
        private void bruteForceAttack(){
            boolean found=false;
            int maxAttempts=Math.min(stolenPrime.intValue()-1,10000);
            System.out.println("   Trying up to " + maxAttempts + " possible keys...");
            for(int guess=2; guess<maxAttempts; guess++){
                BigInteger guessKey=stolenGenerator.modPow(BigInteger.valueOf(guess),stolenPrime);
                
                if(guessKey.equals(stolenPublicKeyA)){
                    System.out.println("Sucess! Found ALice PrivateKey"+guessKey);
                    this.calculatedSecret=stolenPublicKeyB.modPow(BigInteger.valueOf(guess),stolenPrime);
                    this.attackSuccessful = true;
                    found = true;
                    break;
                }
            }
            
            if (!found) {
                System.out.println("   ❌ FAILED: Could not find the private key");
                System.out.println("   (Stopped after " + maxAttempts + " attempts)");
            }
        }
        
        private void demonstrateImpracticalAttack(){
            int sampleAttempts = 1000;
            System.out.println("   Sampling " + sampleAttempts + " attempts...");
            //boolean found=false;
            
            for (int guess = 2; guess < sampleAttempts + 2; guess++) {
                BigInteger guessKey = stolenGenerator.modPow(BigInteger.valueOf(guess), stolenPrime);
                // Just demonstrate the computation
                if (guessKey.equals(stolenPublicKeyA)) {
                    System.out.println("   ✅ SUCCESS: Found Alice's private key: " + guess);
                    this.calculatedSecret = stolenPublicKeyB.modPow(BigInteger.valueOf(guess), stolenPrime);
                    this.attackSuccessful = true;
                    //found = true;
                    break;
                }
            }
        
            long totalPossibleAttempts = stolenPrime.longValue() - 1;
        
            System.out.println("   Total attempts needed: " + totalPossibleAttempts);
            System.out.println("   ❌ IMPRACTICAL: Attack would take too long!");
            System.out.println("   💡 This shows why medium primes provide basic protection");
        }
  

    public void reportResults(BigInteger realSecret){
        System.out.println("\n📊 " + name + "'s FINAL REPORT:");
        System.out.println("   Real shared secret: " + (stolenPrime.bitLength() < 20 ? realSecret : "[Secured by large prime]"));
        System.out.println("   Hacker's success: " + (attackSuccessful ? "❌ BREACHED" : "✅ FAILED"));
        
        if (attackSuccessful) {
            System.out.println("   💀 DANGER: Small primes are vulnerable to brute force!");
            System.out.println("   🔒 SOLUTION: Use primes larger than 2048 bits for real security");
        }else {
            if (stolenPrime.bitLength() <= 32) {
                System.out.println("   ⚠️  WARNING: Medium primes offer limited protection");
                System.out.println("   💡 Upgrade to larger primes for better security");
            }else {
                System.out.println("   ✅ EXCELLENT: Large primes provide strong protection");
                System.out.println("   🔒 The Diffie-Hellman protocol is working securely!");
            }
        }
    }
        
    public boolean wasAttackSuccessful(){
        return attackSuccessful;
    }

}

