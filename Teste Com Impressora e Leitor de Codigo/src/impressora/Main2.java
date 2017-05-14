package impressora;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JTextArea;


public class Main2 {
	public static void main(String[] args){
		Main2 main = new Main2();
		main.a();
	}
	
	public void a() {
	    PageFormat format = new PageFormat();
	    Paper paper = new Paper();

	    double paperWidth = 3;//3.25
	    double paperHeight = 3.69;//11.69
	    double leftMargin = 0.12;
	    double rightMargin = 0.10;
	    double topMargin = 0;
	    double bottomMargin = 0.01;

	    paper.setSize(paperWidth * 200, paperHeight * 200);
	    paper.setImageableArea(leftMargin * 200, topMargin * 200,
	            (paperWidth - leftMargin - rightMargin) * 200,
	            (paperHeight - topMargin - bottomMargin) * 200);

	    format.setPaper(paper);

	    PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
	    aset.add(OrientationRequested.PORTRAIT);


	    PrinterJob printerJob = PrinterJob.getPrinterJob();
	    Printable printable = new ReceiptPrint();

	    format = printerJob.validatePage(format);
	    printerJob.setPrintable(printable, format);
	    try {
	        printerJob.print(aset);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	 

	class ReceiptPrint implements Printable {

	SimpleDateFormat df = new SimpleDateFormat();
	String receiptDetailLine;
	public static final String pspace = "               ";//15-spaces

	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
	        throws PrinterException {

	    df.applyPattern("dd/MM/yyyy HH:mm:ss");
	    String strText = null;
	    final String newLine = "\n";
	    int lineStart;           // start index of line in textarea
	    int lineEnd;             // end index of line in textarea
	    int lineNumber;
	    int lineCount;
	    final String uline = "________________________________________";
	    final String dline = "----------------------------------------";
	    String greetings = "THANKS FOR YOUR VISIT";
	    receiptDetailLine = "asdasdasda";

	    Graphics2D g2d = (Graphics2D) graphics;
	    Font font = new Font("MONOSPACED", Font.BOLD, 9);

	    if (pageIndex < 0 || pageIndex >= 1) {
	        return Printable.NO_SUCH_PAGE;
	    }
	    JTextArea textarea = new JTextArea(10, 40);
	    
	    textarea.append("Página de testes" + "\n");

	    textarea.append(uline + "\n");
	    textarea.append("Order Ref:" + "   " + receiptDetailLine + "\n");
	    textarea.append(dline + "\n");
	    textarea.append("Qty     Description     Price" + newLine);
	    textarea.append(dline + "\n");

	    System.out.println(2);

	    String printedLine = "Service Charge Complimentary";
	    textarea.append(printedLine + newLine);
	    textarea.append(newLine + "Your Reciept\n" + greetings + newLine);
	    textarea.append(df.format(new Date()) + newLine);
	    textarea.setEditable(false);

	    
	    
	    g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

	    g2d.setFont(font);
	    lineNumber = 0;
	    lineCount = textarea.getLineCount();
	    strText = textarea.getText();
	    g2d.setFont(new Font("ROMAN", Font.PLAIN, 8));
	    while (lineCount != 0) {
	        try {
	            lineStart = textarea.getLineStartOffset(lineNumber);
	            lineEnd = textarea.getLineEndOffset(lineNumber);
	            strText = textarea.getText(lineStart, lineEnd - lineStart);
	            System.out.println("To be Print is : " + strText + " :" + lineCount);
	        } catch (Exception ex) {
	            
	        }
	        g2d.drawString(strText, 1, (lineNumber + 1) * 18);
	        lineNumber = lineNumber + 1;
	        lineCount--;
	    }
	    return Printable.PAGE_EXISTS;
	}
	}
}
