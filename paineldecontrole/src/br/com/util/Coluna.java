/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author santos
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Coluna {
    
 
  public String NomeColuna();
  public String MetodoGet() default "";
  public String MetodoSet() default "";
  public boolean IsPK()  default false;
  public TipoPKColuna TipoPK() default TipoPKColuna.NAOSEAPLICA;
  public String Sequence() default "";
  
}
