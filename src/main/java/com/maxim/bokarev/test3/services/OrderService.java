package com.maxim.bokarev.test3.services;

import java.io.ByteArrayOutputStream;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.maxim.bokarev.test3.entities.Order;
import com.maxim.bokarev.test3.entities.OrderEntry;
import com.maxim.bokarev.test3.entities.Product;
import com.maxim.bokarev.test3.repositories.OrderRepository;

@Service
public class OrderService {
	
	private OrderRepository orderRepository;
	
	private ProductService productService;
	
	public Order create(Order order) {
		Order newOrder = new Order();
		newOrder.setNumber(this.getNextNumber());
		newOrder.setBarcode(getBarcode());
		newOrder.setEntries(new HashSet<>());
		
		double orderTotal = 0;
		for(OrderEntry orderEntry : order.getEntries()) {
			Product product = productService.getProduct(orderEntry.getProduct().getSku());
			
			int quantity = orderEntry.getQuantity();
			double totalPrice = quantity * product.getPrice();
			orderTotal += totalPrice;
			
			OrderEntry entry = new OrderEntry();
			entry.setProduct(product);
			entry.setQuantity(quantity);
			entry.setTotalPrice(totalPrice);

			newOrder.getEntries().add(entry);
			
			productService.reduceStockBalance(product.getId(), quantity);
		}
				
		newOrder.setOrderTotal(orderTotal);		
		return orderRepository.save(newOrder);
	}
	
	public Order read(long number) {
		return orderRepository.findByNumber(number);
	}

	public byte[] getInvoice(long number) {
		Order order = orderRepository.findByNumber(number);

		if (order == null) {
			return new byte[0];
		}

		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			Document document = new Document();
			
			PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
			 
			document.open();
			
			document.add(new Paragraph("Invoice #" + order.getNumber()));
			document.add(Chunk.NEWLINE);
			
			PdfPTable table = new PdfPTable(4);
			PdfPCell header = new PdfPCell();
			header.setPhrase(new Phrase("Product"));
			table.addCell(header);
			
			header = new PdfPCell();
			header.setPhrase(new Phrase("Price"));
			table.addCell(header);
			
			header = new PdfPCell();
			header.setPhrase(new Phrase("Quantity"));
			table.addCell(header);
			
			header = new PdfPCell();
			header.setPhrase(new Phrase("Total Price"));
			table.addCell(header);
		    
		    for(OrderEntry entry: order.getEntries()) {
			    table.addCell(String.valueOf(entry.getProduct().getName()));
			    table.addCell(String.valueOf(entry.getProduct().getPrice()));
			    table.addCell(String.valueOf(entry.getQuantity()));
			    table.addCell(String.valueOf(entry.getTotalPrice()));
		    }
		    
		    table.addCell("");
		    table.addCell("");
		    table.addCell("");
		    table.addCell(String.valueOf(order.getOrderTotal()));
			 
			document.add(table);
			
			BarcodeEAN barcodeEAN = new BarcodeEAN();
			barcodeEAN.setCode(String.valueOf(order.getBarcode()));
			barcodeEAN.setCodeType(Barcode.EAN13);
			Image codeEANImage = barcodeEAN.createImageWithBarcode(pdfWriter.getDirectContent(), null, null);
			document.add(Chunk.NEWLINE);
			document.add(codeEANImage);
			
			document.close();
			return out.toByteArray();
		} catch (DocumentException e) {
			throw new RuntimeException(e);
		}
	}

	private Long getNextNumber() {
		return System.currentTimeMillis();
	}

	private String getBarcode() {
		return "3210123456789";
	}

	@Autowired
	public void setOrderRepository(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Autowired
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	

}
