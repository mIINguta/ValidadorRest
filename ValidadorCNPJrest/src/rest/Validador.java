//localhost:8080/CnpjREST/cnpjj?cnpjc=69933227758
package rest;

import java.util.InputMismatchException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/cnpjj")

public class Validador {

@GET
@Produces("text/plain;charset=UTF-8")
public String cnpjj (@QueryParam("cnpjc") String cnpj) {


// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
  if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
   cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
   cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
   cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
   cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
     (cnpj.length() != 14))
     return("Este CNPJ � Inv�lido");

  char dig13, dig14;
  int sm, i, r, num, peso;

// "try" - protege o c�digo para eventuais erros de conversao de tipo (int)
  try {
// Calculo do 1o. Digito Verificador
    sm = 0;
    peso = 2;
    for (i=11; i>=0; i--) {
// converte o i-�simo caractere do CNPJ em um n�mero:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posi��o de '0' na tabela ASCII)
      num = (int)(cnpj.charAt(i) - 48);
      sm = sm + (num * peso);
      peso = peso + 1;
      if (peso == 10)
         peso = 2;
    }

    r = sm % 11;
    if ((r == 0) || (r == 1))
       dig13 = '0';
    else dig13 = (char)((11-r) + 48);

// Calculo do 2o. Digito Verificador
    sm = 0;
    peso = 2;
    for (i=12; i>=0; i--) {
      num = (int)(cnpj.charAt(i)- 48);
      sm = sm + (num * peso);
      peso = peso + 1;
      if (peso == 10)
         peso = 2;
    }

    r = sm % 11;
    if ((r == 0) || (r == 1))
       dig14 = '0';
    else dig14 = (char)((11-r) + 48);

// Verifica se os d�gitos calculados conferem com os d�gitos informados.
    if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
    return("Este CNPJ � V�lido! \n" + cnpj.substring(0, 2) + "." + cnpj.substring(2, 5) + "." +
    cnpj.substring(5, 8) + "." + cnpj.substring(8, 12) + "-" +
    cnpj.substring(12, 14));
     }

    else
    return("Este CNPJ � Inv�lido");
  } catch (InputMismatchException erro) {
       return("Este CNPJ � Inv�lido");
  }}
}