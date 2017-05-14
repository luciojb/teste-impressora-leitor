package impressora;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class Main {
	private static String portaImpressora;
	private static StringBuffer destino = new StringBuffer();
	public static void setPortaImpressora(String porta) {
		Main.portaImpressora = porta;
	}
	public static String getPortaImpressora() {
		return Main.portaImpressora;
	}
	public void adicionar(String texto) {
		Main.destino.append(texto);
	}
	public void darEspaco(int qtde) {
		Main.destino.append(getEspaco(qtde));
	}
	public void quebrarLinha() {
		Main.destino.append("\n");
	}
	public void quebrarLinha(int qtde) {
		for (int x = 1; x <= qtde; x++) {
			quebrarLinha();
		}
	}
	public String getEspaco(int qtde) {
		String result = "";
		for (int x = 1; x <= qtde; x++) {
			result += " ";
		}
		return result;
	}
	public String alinharCentro(String texto, int tamanho) {
	      int inicio;
	      int fim;
	      int diferenca;
	      if (texto == null) {
	    	  return getEspaco(tamanho);
	      } else {
		      if (texto.length() > tamanho) {
		          return texto.substring(0, tamanho);
		      } else {
		          diferenca = tamanho - texto.length();
		          inicio = diferenca / 2;
		          fim = diferenca - inicio;
		          return getEspaco(inicio) + texto + getEspaco(fim);
		      }
	      }
	}
	public String alinharDireita(String texto, int tamanho) {
	      int diferenca;
	      if (texto == null) {
	    	  return getEspaco(tamanho);
	      } else {
		      if (texto.length() > tamanho) {
		          return texto.substring(0, tamanho);
		      } else {
		          diferenca = tamanho - texto.length();
		          return getEspaco(diferenca) + texto;
		      }
	      }
	}
	public String alinharEsquerda(Integer inteiro, int tamanho) {
		if (inteiro != null) {
			return alinharEsquerda(inteiro.toString(), tamanho);
		} else {
			return getEspaco(tamanho);
		}
	}
	public String alinharEsquerda(String texto, int tamanho) {
	      int diferenca;
	      if (texto == null) {
	    	  return getEspaco(tamanho);
	      } else {
		      if (texto.length() > tamanho) {
		          return texto.substring(0, tamanho);
		      } else {
		          diferenca = tamanho - texto.length();
		          return texto + getEspaco(diferenca);
		      }
	      }
	}
	public void adicionarNegrito(String texto) {
		adicionar("\u001B" + "E" + texto + "\u001B" + "F");
	}
	public void imprimir() {
		String portaImpressora = "LPT1";
		FileOutputStream fos = null;
		PrintStream ps = null;
		// Abre a impressora
		try {
		     fos = new FileOutputStream(portaImpressora);
		     ps = new PrintStream(fos);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		// Imprime o conteúdo
		ps.print(Main.destino);
		ps.print("\f");
		// Fecha a impressora 
		try {
			ps.close();
			fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String args[]){
		Main m = new Main();
		m.imprimir();
	}
}