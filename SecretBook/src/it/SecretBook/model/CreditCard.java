package it.SecretBook.model;

import java.time.LocalDate;

public class CreditCard {
  
  private long cod;
  private String intestatario;
  private int cvc;
  private LocalDate scad;
  private double saldo;
  
  public CreditCard() {
    
    cod= -1;
    intestatario= "";
    cvc= -1;
    scad= LocalDate.MIN;
    saldo= 0;
    
  }

  public CreditCard(long cod, String intestatario, int cvc, LocalDate scad, double saldo) {
    this.cod = cod;
    this.intestatario = intestatario;
    this.cvc = cvc;
    this.scad = scad;
    this.saldo = saldo;
  }

  public long getCod() {
    return cod;
  }

  public void setCod(long cod) {
    this.cod = cod;
  }

  public String getIntestatario() {
    return intestatario;
  }

  public void setIntestatario(String intestatario) {
    this.intestatario = intestatario;
  }

  public int getCvc() {
    return cvc;
  }

  public void setCvc(int cvc) {
    this.cvc = cvc;
  }

  public LocalDate getScad() {
    return scad;
  }

  public void setScad(LocalDate scad) {
    this.scad = scad;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }
  
  

}//Untangled partizione: 17