import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.print.DocFlavor.URL;
import javax.swing.*;
import javax.swing.Box.Filler;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class menu extends JPanel{
	
	// --------------------------------------------------------------------------------------------------------------
	// CAJAS LAYOUT Y MENÚS PRINCIPALES
	// -------------------------------------------------------------------------------------------------------------------
		
	static JComboBox grupoSeleccionBloques;
	static JComboBox tipo_doc;
	
	static Box layout_principal = Box.createVerticalBox();
	
	static Box menu_principal = Box.createHorizontalBox();
	static Box herramientas = Box.createHorizontalBox();
		
	static JButton config;
	static JButton retirar;
	static JButton nuevo;
	static JButton exportar;
	static JButton añadir;
	static JButton importar;
	static JButton guardar;
	static JCheckBox modo_textual;
	
	// ---------------------------------------------------------------------------------------------------------------
	// VARIABLES NECESARIAS PARA LAS HERRAMIENTAS
	// -------------------------------------------------------------------------------------------------------------------
	
	// PARRAFO:
	
	static String[] fuentes_disponibles = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
	static String[] tamaños_disponibles = {"1","2","3","4","5","6","7"};
	
	static JButton fuentes_boton = new JButton("Fuentes de letra");
	static JList lista_fuentes = new JList(fuentes_disponibles);
	static JScrollPane lista_fuentes_scroll = new JScrollPane(lista_fuentes);
	static JButton aceptar = new JButton("Aceptar");
	static JButton boton_color_letra = new JButton();
	
	static JComboBox tamaño_letra = new JComboBox(tamaños_disponibles);
	static JColorChooser color_letra_select = new JColorChooser();
	
	static JButton negrita;
	static JButton subrayado;
	static JButton cursiva;
	
	static JLabel label_fuente_de_letra = new JLabel("Fuente de letra");
	static JLabel label_tamaño_de_letra = new JLabel("Tamaño de letra");
	static JLabel label_color_de_letra = new JLabel("Color de letra");
	
	static JButton pos_izquierda;
	static JButton pos_centrada;
	static JButton pos_derecha;
	static JButton justificado;
	
	static JButton hipervinculo;
		
	// ENCABEZADO
	
	static JLabel label_tipo_encabezado = new JLabel("Tipo");
	static String[] lista_encabezado = {"h1", "h2", "h3", "h4", "h5", "h6"};
	static JComboBox tipoEncabezado = new JComboBox(lista_encabezado);
	
	// IMAGEN
	
	static JLabel label_ancho_img = new JLabel("Ancho:");
	static JSpinner anchuraImagen = new JSpinner(new SpinnerNumberModel());
	static JLabel label_alto_img = new JLabel("Alto:");
	static JSpinner alturaImagen = new JSpinner(new SpinnerNumberModel());
	static JLabel label_inf_tam_img = new JLabel("Resolución 0 = Resolución original");
	static JButton cambiarResolucion = new JButton("Cambiar resolución");
	
	// TABLAS
	
	static JLabel celdasL = new JLabel("Nº de celdas");
	static JSpinner celdas = new JSpinner(new SpinnerNumberModel());
	static JLabel colsL = new JLabel("Nº de columnas");
	static JSpinner columnas = new JSpinner(new SpinnerNumberModel());
	static JSpinner ancho_celda = new JSpinner(new SpinnerListModel());
	static JSpinner alto_celda = new JSpinner(new SpinnerListModel());
	static JSpinner tamaño_borde_tabla = new JSpinner(new SpinnerListModel());
	
	// -------------------------------------------------------------------------------------------------------------------
	// VARIABLES NECESARIAS PARA GESTIÓN DE BLOQUES
	// -------------------------------------------------------------------------------------------------------------------
	
	static List <Component> grupoBloquesPrincipales = new ArrayList <Component>();
	static List <Component> grupoBloquesSecundarios = new ArrayList <Component>();
	
	static Object seleccionEncabezados;
	static Object encabezados_doc_seleccion;
	static Object seleccion = null;
	
	public void inicializadorComponentes(boolean modo_textual_var) {
					
		if (modo_textual_var) {
				
			nuevo = new JButton("Añadir debajo");
			añadir = new JButton("Añadir al lado");
			exportar = new JButton("Exportar");
			retirar = new JButton("Retirar");
			modo_textual = new JCheckBox("Modo textual", true);
			
			fuentes_boton = new JButton("Fuentes de letra");
			negrita = new JButton("Negrita");
			subrayado = new JButton("Subrayado");
			cursiva = new JButton("Cursiva");
			pos_izquierda = new JButton("IZQUIERDA");
			pos_centrada = new JButton("CENTRO");
			pos_derecha = new JButton("DERECHA");
			justificado = new JButton("JUSTIFICACIÓN");
			hipervinculo = new JButton("URL");
				
		} 
			
		else {
						
			nuevo = new JButton(new ImageIcon(getClass().getResource("nuevo.png")));
			añadir = new JButton(new ImageIcon(getClass().getResource("añadir.png")));
			exportar = new JButton(new ImageIcon(getClass().getResource("exportar.png")));
			retirar = new JButton(new ImageIcon(getClass().getResource("retirar.png")));
			fuentes_boton = new JButton(new ImageIcon(getClass().getResource("fuente.png")));
			negrita = new JButton(new ImageIcon(getClass().getResource("negrita.png")));
			subrayado = new JButton(new ImageIcon(getClass().getResource("subrayado.png")));
			cursiva = new JButton(new ImageIcon(getClass().getResource("italica.png")));
			pos_izquierda = new JButton(new ImageIcon(getClass().getResource("izquierda.png")));
			pos_centrada = new JButton(new ImageIcon(getClass().getResource("centro.png")));
			pos_derecha = new JButton(new ImageIcon(getClass().getResource("derecha.png")));
			justificado = new JButton(new ImageIcon(getClass().getResource("justificado.png")));

			modo_textual = new JCheckBox("Modo textual", false);
		
		}
			
		nuevo.addActionListener(new NuevoBloque(true));
		exportar.addActionListener(new exportarDocumento());
		
	}
	
	public void añadirElementosPrincipales() {
		
		// ---------------- LABEL: BLOQUE -------------------------
			
		JLabel label1 = new JLabel("BLOQUE");
		menu_principal.add(label1);
		menu_principal.add(Box.createHorizontalStrut(3));
		
		// ---------------- JComboBox BLOQUE ----------------------
		
		grupoSeleccionBloques = new JComboBox();
		grupoSeleccionBloques.addItem("Párrafo");
		grupoSeleccionBloques.addItem("Encabezado");
		grupoSeleccionBloques.addItem("Imagen");
		grupoSeleccionBloques.addItem("Tabla");
		
		menu_principal.add(grupoSeleccionBloques);
		
		grupoSeleccionBloques.addItemListener(new eleccion_bloque());
		menu_principal.add(Box.createHorizontalStrut(2));
		
		// ---------------- Boton de Nuevo bloque ------------------------------
		
		menu_principal.add(nuevo);
		
		// ---------------- Boton de añadir ------------------------------
		
		menu_principal.add(añadir);
		añadir.addActionListener(new NuevoBloque(false));
		
		// ---------------- Boton de Retirar ------------------------------
		
		menu_principal.add(retirar);
		retirar.addActionListener(new eliminacionBloque());
		
		// ---------------- Boton de exportar ----------------------------
		
		menu_principal.add(exportar);
				
		// -----------------Boton de cambio de interfaz------------------
		
		cambiarAspectoMenu();
		
	}
	
	public void cambiarAspectoMenu() {
		
		menu_principal.add(modo_textual);
		modo_textual.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				menu_principal.removeAll();
				
				if (modo_textual.isSelected()==false) {
					
					inicializadorComponentes(false);
				}
				
				else {
					
					inicializadorComponentes(true);
				}
				
				añadirElementosPrincipales();
				seleccionBloque();
				
			}
		});
	}
	
	
	public menu() {
				
		add(layout_principal);
		layout_principal.add(menu_principal);
		layout_principal.add(Box.createVerticalStrut(5));
		
		inicializadorComponentes(false);
		añadirElementosPrincipales();
		
		if (grupoSeleccionBloques.getSelectedItem().equals("Párrafo")) {
			
			parrafo();
		}
	}
	
	// -------------------------------------------------------------------------------------------------
	// CREACION DE LA BARRA DE HERRAMIENTAS PARA PÁRRAFOS
	// -------------------------------------------------------------------------------------------------
	
	public static void texto() {
		
		layout_principal.add(herramientas);		
		herramientas.add(fuentes_boton);
		herramientas.add(Box.createHorizontalStrut(5));
		fuentes_boton.addActionListener(new fuentes_popup());
		
		herramientas.add(label_color_de_letra);
		herramientas.add(Box.createHorizontalStrut(3));
		
		herramientas.add(boton_color_letra);
		boton_color_letra.addActionListener(new cambio_color_letra());
		boton_color_letra.setBackground(Color.black);
				
		herramientas.add(Box.createHorizontalStrut(5));
		
		herramientas.add(pos_izquierda);
		herramientas.add(pos_centrada);
		herramientas.add(pos_derecha);
		herramientas.add(justificado);
		
		herramientas.add(negrita);
		herramientas.add(subrayado);
		herramientas.add(cursiva);
		
		negrita.addActionListener(new StyledEditorKit.BoldAction());
		cursiva.addActionListener(new StyledEditorKit.ItalicAction());
		subrayado.addActionListener(new StyledEditorKit.UnderlineAction());
				
		pos_izquierda.addActionListener(new StyledEditorKit.AlignmentAction("alineacion", 0));
		pos_centrada.addActionListener(new StyledEditorKit.AlignmentAction("alineacion", 1));
		pos_derecha.addActionListener(new StyledEditorKit.AlignmentAction("alineacion", 2));
		justificado.addActionListener(new StyledEditorKit.AlignmentAction("alineacion", 3));
		
		herramientas.add(Box.createHorizontalStrut(5));
		
	}
	
	public void encabezado() {
		
		herramientas.add(label_tipo_encabezado);
		herramientas.add(tipoEncabezado);
		texto();
	
	}
	
	public void parrafo() {
		
		herramientas.add(label_tamaño_de_letra);
		herramientas.add(Box.createHorizontalStrut(3));
		herramientas.add(tamaño_letra);
		herramientas.add(Box.createHorizontalStrut(5));
		texto();
	}
	
	public void imagen() {
		
		 herramientas.add(label_ancho_img);
		 herramientas.add(anchuraImagen);
		 herramientas.add(Box.createHorizontalStrut(3));
		 herramientas.add(label_alto_img);
		 herramientas.add(alturaImagen);
		 herramientas.add(Box.createHorizontalStrut(3));
		 herramientas.add(cambiarResolucion);
		 herramientas.add(Box.createHorizontalStrut(5));
		 herramientas.add(label_inf_tam_img);
	}
	
	public void tabla() {
		
		herramientas.add(celdasL);
		herramientas.add(Box.createHorizontalStrut(2));
		herramientas.add(celdas);
		herramientas.add(Box.createHorizontalStrut(2));
		herramientas.add(colsL);
		herramientas.add(Box.createHorizontalStrut(2));
		herramientas.add(columnas);
	}
	
	
	public void seleccionBloque() {
		
		herramientas.removeAll();
		
		if (grupoSeleccionBloques.getSelectedItem().equals("Párrafo")) {
			
			parrafo();
		}
		
		if (grupoSeleccionBloques.getSelectedItem().equals("Encabezado")) {
							
			encabezado();
		}
		
		if (grupoSeleccionBloques.getSelectedItem().equals("Imagen")) {
			
			imagen();
		}
		
		if (grupoSeleccionBloques.getSelectedItem().equals("Tabla")) {
			
			tabla();
		}
		
		updateUI();
	}
	
	// ---------------------------------------------------------------------------------------------------------------------
	// CREACIÓN DE NUEVOS BLOQUES AL ÁREA
	// ----------------------------------------------------------------------------------------------------------------------
	
	private class NuevoBloque implements ActionListener {
		
		boolean bloque;
		
		public NuevoBloque(boolean bloque) {
			
			this.bloque=bloque;
		}		
		
		public Component SetPanelParrafo() {
					
			// -------------------------------------------------------------------------------------
			// DICCIONARIO DE TAMAÑOS DE LETRA PX/HTML
			// -------------------------------------------------------------------------------------
			
			HashMap <String,String> diccionarioTamanosLetra = new HashMap<String,String>();
			diccionarioTamanosLetra.put("1", "9");
			diccionarioTamanosLetra.put("2", "11");
			diccionarioTamanosLetra.put("3", "14");
			diccionarioTamanosLetra.put("4", "16");
			diccionarioTamanosLetra.put("5", "20");
			diccionarioTamanosLetra.put("6", "26");
			diccionarioTamanosLetra.put("7", "80");

			// -------------------------------------------------------------------------------------
			// PROPIEDADES DE PANEL DE PÁRRAFOS
			// -------------------------------------------------------------------------------------
			
			JTextPane panel = new JTextPane();
			
			panel.setContentType("text/html");
			Document panel_doc = panel.getDocument();								
			panel.setMargin(new Insets(0, 40, 0, 40));
			panel.setBackground(Color.white);
			panel.setSelectedTextColor(Color.blue);
										
			// ------------------------------------------------------------------------------------------
			// ASIGNACIÓN DEL TAMAÑO DE LETRA AUTOMÁTICAMENTE
			// ------------------------------------------------------------------------------------------
											
			panel_doc.addDocumentListener(new DocumentListener() {

				public void insertUpdate(DocumentEvent e) {
					
					SwingUtilities.invokeLater(new correr(e));
				}

				public void removeUpdate(DocumentEvent e) {
					
					SwingUtilities.invokeLater(new correr(e));
				}
				
				public void changedUpdate(DocumentEvent e) {					
				}
				
				class correr implements Runnable{
					
					DocumentEvent e;
					
					public correr(DocumentEvent e) {
						
						this.e=e;
					}

					public void run() {
						
						int posicion = panel.getCaret().getMark();
						
						if (panel_doc.getLength()==1) {
							
							panel.moveCaretPosition(0);
															
						} else {
							
							panel.moveCaretPosition(posicion);
						}
						
						String tamanoLetraSeleccionado = (String)tamaño_letra.getSelectedItem();
						int tamanoLetraDiccionario = Integer.parseInt(diccionarioTamanosLetra.get(tamanoLetraSeleccionado));
						
						Action fuentePorDefecto = new StyledEditorKit.FontSizeAction("fuente_defecto", tamanoLetraDiccionario);
						fuentePorDefecto.actionPerformed(null);
						panel.moveCaretPosition(posicion);

					}
				}
			});
			
			// ------------------------------------------------------------------------------------------------------------------
			// SELECCCION.
			// ------------------------------------------------------
			
			panel.addFocusListener(new FocusListener() {

				public void focusGained(FocusEvent e) {
					
					seleccion = panel;
				}

				public void focusLost(FocusEvent e) {}
				
			});
			
			// ------------------------------------------------------------------------------------------------------------------
			// AJUSTE MANUAL DEL TAMAÑO DE LA LETRA
			// ------------------------------------------------------------------------------------------------------------------

			tamaño_letra.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					
					Action cambio_tamaño = new StyledEditorKit.FontSizeAction((String)tamaño_letra.getSelectedItem(), Integer.parseInt(diccionarioTamanosLetra.get((String)tamaño_letra.getSelectedItem())));
					cambio_tamaño.actionPerformed(e);
				}
			});
			
			panel.setName("parrafo");
			return panel;
		}
		
		public Component setPanelEncabezado() {
			
			// -------------------------------------------------------------------------------------------------
			// PROPIEDADES DEL PANEL DE ENCABEZADO
			// -------------------------------------------------------------------------------------------------
			
			JTextPane panel = new JTextPane();
			panel.setContentType("text/html");
			panel.setMargin(new Insets(0, 40, 0, 40));
			Document panel_doc = panel.getDocument();
			
			// ------------------------------------------
			// SELECCIÓN DE UN ENCABEZADO
			// ------------------------------------------
			
			panel.addFocusListener(new FocusListener() {

				public void focusGained(FocusEvent e) {
					
					seleccionEncabezados = e.getComponent();
					encabezados_doc_seleccion = ((JTextPane)seleccionEncabezados).getDocument();
					seleccion = panel;
				}

				public void focusLost(FocusEvent e) {}
				
			});
			
			// -------------------------------------------------------------------------------------------------
			// ASIGNACIÓN AUTOMÁTICA DEL TIPO DE ENCABEZADO (H1, H2, H3 ... )
			// -------------------------------------------------------------------------------------------------
			
			panel_doc.addDocumentListener(new DocumentListener() {

				public void insertUpdate(DocumentEvent e) {
											
					SwingUtilities.invokeLater(new correr(e));
				}

				public void removeUpdate(DocumentEvent e) {}
				public void changedUpdate(DocumentEvent e) {}
				
				class correr implements Runnable{
					
					DocumentEvent e;
					
					public correr(DocumentEvent e) {
						
						this.e=e;
					}

					public void run() {
						
						int tamanoTexto = panel_doc.getLength();
																		
						if (tamanoTexto==e.getLength()) {
							
							String encabezadoSeleccionado = (String)tipoEncabezado.getSelectedItem();
													
							try {
								
								String textoBloque = panel_doc.getText(0, tamanoTexto);
								panel.setText("<" + encabezadoSeleccionado + ">" + textoBloque + "</" + encabezadoSeleccionado + ">");
							
							} catch (BadLocationException excepcion) {}
						}
					}
				}
			});
			
			// -----------------------------------------------------------------------------------------------------------
			// ASIGNACIÓN MANUAL DEL TIPO DE ENCABEZADO
			// -----------------------------------------------------------------------------------------------------------
			
			class CambioEncabezado implements ActionListener{

				public void actionPerformed(ActionEvent e) {
					
					try {
						
						JTextPane elementoEncabezado = (JTextPane) seleccionEncabezados;
						String encabezadoSeleccionado = (String)tipoEncabezado.getSelectedItem();
						
						Document documentoEncabezado = (Document)encabezados_doc_seleccion;
						int tamanoDocumentoEncabezado = documentoEncabezado.getLength();
								
						String contenidoEncabezado = documentoEncabezado.getText(0, tamanoDocumentoEncabezado);
						
						elementoEncabezado.setText("<" + encabezadoSeleccionado + ">" + contenidoEncabezado + "</" + encabezadoSeleccionado + ">");
					
					} catch (BadLocationException e1) {}	
				}
			}
			
			tipoEncabezado.addActionListener(new CambioEncabezado());
			panel.setName("encabezado");
			
			return panel;
			
		}
		
		public Component setPanelImagen() {
						
			// --------------------------------------------------------------------------------
			// PROPIEDADES DEL PANEL IMAGEN
			// --------------------------------------------------------------------------------
			
			JFileChooser archivo = new JFileChooser();
			int seleccion = archivo.showOpenDialog(Principal.area);

			
			if (seleccion == JFileChooser.APPROVE_OPTION) {

				JLabel panel = new JLabel();
				int pixelesAnchuraImagen = (int)anchuraImagen.getValue();
				int pixelesAlturaImagen = (int)alturaImagen.getValue();
				
				File archivoSeleccionado = archivo.getSelectedFile();
				String rutaArchivoSeleccionado = archivoSeleccionado.getAbsolutePath();
				
				if (pixelesAnchuraImagen>0 && pixelesAlturaImagen>0) {
					
					panel.setName(String.format("<img width=\"" + pixelesAnchuraImagen + "px\" height=\"" + pixelesAlturaImagen + "px\" src=\"" + rutaArchivoSeleccionado + "\">"));
					BufferedImage bufferImagen;
					
					try {
						
						File archivoImagen = new File(rutaArchivoSeleccionado);
						
						bufferImagen = ImageIO.read(archivoImagen);
						Image imagen = bufferImagen.getScaledInstance(pixelesAnchuraImagen, pixelesAlturaImagen, Image.SCALE_DEFAULT);
						ImageIcon graficosImagen = new ImageIcon(imagen);
						panel.setIcon(graficosImagen);
						
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				else {
					
					panel.setName("<img src=\"" + rutaArchivoSeleccionado  + "\">");
					
					ImageIcon graficosImagen = new ImageIcon(rutaArchivoSeleccionado);
					panel.setIcon(graficosImagen);
					
					anchuraImagen.setValue(graficosImagen.getIconWidth());
					alturaImagen.setValue(graficosImagen.getIconHeight());
					
				}
				
				cambiarResolucion.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						
						if (pixelesAnchuraImagen>0 && pixelesAlturaImagen>0){
							
							BufferedImage bufferImagen;
							
							try {
								
								File archivoImagen = new File(rutaArchivoSeleccionado);
								
								bufferImagen = ImageIO.read(archivoImagen);
								Image imagen = bufferImagen.getScaledInstance(pixelesAnchuraImagen, pixelesAlturaImagen, Image.SCALE_DEFAULT);
								ImageIcon icono = new ImageIcon(imagen);
								panel.setIcon(icono);
								
								
							} catch (IOException error) {}
						}
						
						else {
							
							JOptionPane.showMessageDialog(panel, "La resolución indicada no es válida");
						}
					}
				});
		        
				
				return panel;
								
			}
			
			else {
				
				return null;
				
			}
		}
		
		public Component setPanelTabla() {
			
			int numeroCeldas = (int)celdas.getValue();
			int numeroColumnas = (int)columnas.getValue();
			
			if (numeroCeldas>0 && numeroColumnas>0) {
				
				JTextPane panel = new JTextPane();
				panel.setContentType("text/html");
				panel.setEditable(false);
				
				panel.addFocusListener(new FocusListener() {

					public void focusGained(FocusEvent e) {
						
						seleccion = panel;
						
					}

					public void focusLost(FocusEvent e) {}
					
				});
					
									
				String tablaHTML = "";
				tablaHTML = tablaHTML + "<table border=\"2\">";
				for (int i = 0;i<numeroColumnas;i++) {
										
					tablaHTML = tablaHTML + "<tr>";
					
					for (int h = 0;h<numeroCeldas;h++) {
						
						tablaHTML = tablaHTML + "<td>" + "Celda " + h + ":" + i + "</td>";
					}
					
					tablaHTML = tablaHTML + "</tr>";
					
				}
				
				tablaHTML = tablaHTML + "</table>";
				panel.setText(tablaHTML);
				panel.setName("tabla");
				return panel;
				
			}
			
			else {
				
				JOptionPane.showMessageDialog(area.espacio_trabajo, "Introduzca un valor válido");
				return null;
			}
			
		}
		
		public Component setPanelGeneral() {
			
			Component panel;
			panel = null;
			
			Object bloqueSeleccionado = menu.grupoSeleccionBloques.getSelectedItem();
			
			if (bloqueSeleccionado.equals("Párrafo")) {
				
				panel = SetPanelParrafo();
			}
	
			else if (bloqueSeleccionado.equals("Encabezado")) {
								
				panel = setPanelEncabezado();
			}
			
			else if (bloqueSeleccionado.equals("Imagen")) {
				
				panel = setPanelImagen();
			}
			
			else if (bloqueSeleccionado.equals("Tabla")) {
				
				panel = setPanelTabla();
			}
						
			return panel;
			
		}

    // (........)
    // EL RESTO DEL CÓDIGO SE ENCUENTRA OCULTO POR CUJESTIONES DE PROPIEDAD INTELECTUAL
