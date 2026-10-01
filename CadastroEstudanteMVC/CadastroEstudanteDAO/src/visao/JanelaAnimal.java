package visao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.BorderLayout;

import net.miginfocom.swing.MigLayout;

import dao.AnimalDAO;
import modelo.Animal;

public class JanelaAnimal extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField txtBusca;

	private JTable tabela;
	private JLabel lblStatus;

	// ==============================
	// CARD LAYOUT - TELA DA DIREITA
	// ==============================

	private JPanel painelDireita;
	private JPanel painelCards;
	private CardLayout cardLayout;

	private JButton btnMenuCadastrar;
	private JButton btnMenuEditar;
	private JButton btnMenuLista;

	// Campos da tela CADASTRAR
	private JTextField txtNomeDireita;
	private JTextField txtEspecieDireita;
	private JTextField txtTamanhoDireita;
	private JTextField txtPesoDireita;
	private JTextField txtNascimentoDireita;
	private JTextField txtDietaDireita;

	// Campos exclusivos da tela EDITAR
	private JTextField txtNomeEditar;
	private JTextField txtEspecieEditar;
	private JTextField txtTamanhoEditar;
	private JTextField txtPesoEditar;
	private JTextField txtNascimentoEditar;
	private JTextField txtDietaEditar;

	// ==============================
	// MAIN
	// ==============================

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaAnimal frame = new JanelaAnimal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// ==============================
	// CONSTRUTOR
	// ==============================

	public JanelaAnimal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 631);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(new MigLayout("", "[grow][grow][320px]", "[59.00px,grow][30px][grow][25px]"));

		// =========================================================
		// PAINEL DA DIREITA
		// =========================================================

		painelDireita = new JPanel(new BorderLayout());

		contentPane.add(
				painelDireita,
				"cell 2 0 1 4,grow"
		);

		// Menu superior
		JPanel menuDireita = new JPanel(new GridLayout(1, 3));

		painelDireita.add(
				menuDireita,
				BorderLayout.NORTH
		);

		// =========================================================
		// CARD LAYOUT
		// =========================================================

		cardLayout = new CardLayout();

		painelCards = new JPanel(cardLayout);

		painelDireita.add(
				painelCards,
				BorderLayout.CENTER
		);

		// Cada tela possui seus próprios componentes
		painelCards.add(
				criarTelaCadastrar(),
				"CADASTRAR"
		);

		painelCards.add(
				criarTelaEditar(),
				"EDITAR"
		);

		painelCards.add(
				criarTelaLista(),
				"LISTA"
		);

		// Tela inicial
		cardLayout.show(
				painelCards,
				"CADASTRAR"
		);

		// =========================================================
		// STATUS
		// =========================================================

		lblStatus = new JLabel("Mensagens status");

		contentPane.add(
				lblStatus,
				"cell 0 3,growx,aligny top"
		);

		// =========================================================
		// TABELA
		// =========================================================

		tabela = new JTable();

		JScrollPane tabelaAnimal = new JScrollPane(tabela);

		contentPane.add(
				tabelaAnimal,
				"cell 0 2 2 1,grow"
		);

		modelo = new DefaultTableModel(
				new String[] {
						"id",
						"Nome",
						"Especie",
						"Tamanho",
						"Peso",
						"Nascimento",
						"Dieta"
				},
				0
		);

		tabela.setModel(modelo);

		tabela.setRowHeight(22);

		// Impede edição direta da tabela
		tabela.setDefaultEditor(
				Object.class,
				null
		);

		// =========================================================
		// BOTÕES DA TELA ESQUERDA
		// =========================================================

		JPanel panel = new JPanel();

		contentPane.add(
				panel,
				"cell 0 1 2 1,grow"
		);

		panel.setLayout(
				new GridLayout(0, 4, 0, 0)
		);

		JButton btnExcluir = new JButton("Excluir");

		panel.add(btnExcluir);

		JButton btnLimpar = new JButton("Limpar");

		panel.add(btnLimpar);
		btnMenuLista = new JButton("Lista");
		panel.add(btnMenuLista);
		
				// =========================================================
				// BOTÃO BUSCAR
				// =========================================================
		
				JButton btnBuscar = new JButton("Buscar");
				panel.add(btnBuscar);
				
						btnBuscar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
				
								buscar();
							}
						});
		
				btnMenuLista.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
		
						listar();
		
						cardLayout.show(
								painelCards,
								"LISTA"
						);
					}
				});

		// =========================================================
		// FORMULÁRIO ESQUERDO
		// =========================================================

		JPanel panel_1 = new JPanel();

		contentPane.add(
				panel_1,
				"cell 0 0 2 1,growx,aligny top"
		);
				panel_1.setLayout(new MigLayout("", "[81px][61px][][363px]", "[23px][20px]"));
		
				btnMenuCadastrar = new JButton("Cadastrar");
				panel_1.add(btnMenuCadastrar, "cell 0 0,alignx left,aligny top");
				
						// =========================================================
						// BOTÕES DO MENU DO CARD
						// =========================================================
				
						btnMenuCadastrar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
				
								limparTelaCadastrar();
				
								cardLayout.show(
										painelCards,
										"CADASTRAR"
								);
							}
						});
		btnMenuEditar = new JButton("Editar");
		panel_1.add(btnMenuEditar, "cell 1 0,alignx left,aligny top");
		
		btnMenuEditar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        int linha = tabela.getSelectedRow();

		        if (linha < 0) {

		            JOptionPane.showMessageDialog(
		                    JanelaAnimal.this,
		                    "Selecione um animal na tabela primeiro.",
		                    "Aviso",
		                    JOptionPane.WARNING_MESSAGE
		            );

		            return;
		        }

		        int linhaModelo =
		                tabela.convertRowIndexToModel(linha);

		        idSelecionado =
		                ((Number) modelo.getValueAt(linhaModelo, 0)).intValue();

		        // Preenche os campos da tela EDITAR
		        txtNomeEditar.setText(
		                String.valueOf(
		                        modelo.getValueAt(linhaModelo, 1)
		                )
		        );

		        txtEspecieEditar.setText(
		                String.valueOf(
		                        modelo.getValueAt(linhaModelo, 2)
		                )
		        );

		        txtTamanhoEditar.setText(
		                String.valueOf(
		                        modelo.getValueAt(linhaModelo, 3)
		                )
		        );

		        txtPesoEditar.setText(
		                String.valueOf(
		                        modelo.getValueAt(linhaModelo, 4)
		                )
		        );

		        txtNascimentoEditar.setText(
		                String.valueOf(
		                        modelo.getValueAt(linhaModelo, 5)
		                )
		        );

		        txtDietaEditar.setText(
		                String.valueOf(
		                        modelo.getValueAt(linhaModelo, 6)
		                )
		        );

		        cardLayout.show(
		                painelCards,
		                "EDITAR"
		        );
		    }
		});

		JLabel lblNewLabel_3 = new JLabel("Buscar:");

		panel_1.add(
				lblNewLabel_3, "cell 0 1,alignx left,aligny center"
		);

		txtBusca = new JTextField();

		panel_1.add(
				txtBusca, "flowx,cell 1 1 3 1,growx,aligny top"
		);

		txtBusca.setColumns(10);

		// =========================================================
		// AÇÕES DOS BOTÕES ESQUERDOS
		// =========================================================

		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				txtBusca.setText("");

				limpar();
			}
		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				excluir();
			}
		});

		// =========================================================
		// SELEÇÃO DA TABELA
		// =========================================================

		tabela.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {

					public void valueChanged(ListSelectionEvent e) {

						if (!e.getValueIsAdjusting()) {

							carregarSelecionado();
						}
					}
				}
		);

		// Lista inicial
		listar();
	}

	// =========================================================
	// CARREGAR SELECIONADO
	// =========================================================



	private void carregarSelecionado() {

	    int linha = tabela.getSelectedRow();

	    if (linha < 0) {
	        return;
	    }

	    int linhaModelo =
	            tabela.convertRowIndexToModel(linha);

	    idSelecionado =
	            ((Number) modelo.getValueAt(linhaModelo, 0)).intValue();

	    lblStatus.setText(
	            "Animal selecionado: id "
	                    + idSelecionado
	                    + "."
	    );
	}


	// =========================================================
	// EXCLUIR
	// =========================================================

	private void excluir() {

	    int linha = tabela.getSelectedRow();

	    if (linha < 0) {

	        JOptionPane.showMessageDialog(
	                this,
	                "Selecione primeiro uma linha da tabela.",
	                "Aviso",
	                JOptionPane.WARNING_MESSAGE
	        );

	        return;
	    }

	    int linhaModelo =
	            tabela.convertRowIndexToModel(linha);

	    int id =
	            ((Number) modelo.getValueAt(linhaModelo, 0)).intValue();

	    String nome =
	            String.valueOf(
	                    modelo.getValueAt(linhaModelo, 1)
	            );

	    int opcao =
	            JOptionPane.showConfirmDialog(
	                    this,
	                    "Excluir o animal " + nome + "?",
	                    "Confirmação",
	                    JOptionPane.YES_NO_OPTION
	            );

	    if (opcao != JOptionPane.YES_OPTION) {
	        return;
	    }

	    try {

	        dao.excluir(id);

	        JOptionPane.showMessageDialog(
	                this,
	                "Animal excluído."
	        );

	        idSelecionado = 0;

	        listar();

	        tabela.clearSelection();

	        lblStatus.setText(
	                "Animal excluído com sucesso."
	        );

	    } catch (SQLException ex) {

	        erro(
	                "Erro ao excluir",
	                ex
	        );
	    }
	}

	// =========================================================
	// GETTERS
	// =========================================================


	public JTextField getTxtBusca() {
		return txtBusca;
	}

	public JTable getTabela() {
		return tabela;
	}

	public JLabel getLblStatus() {
		return lblStatus;
	}

	// =========================================================
	// DAO / VARIÁVEIS
	// =========================================================

	private DefaultTableModel modelo;

	private final AnimalDAO dao =
			new AnimalDAO();

	private int idSelecionado = 0;

	// =========================================================
	// LISTAR
	// =========================================================

	private void listar() {

		try {

			preencherTabela(
					dao.listar()
			);

		} catch (SQLException ex) {

			erro(
					"Erro ao listar",
					ex
			);
		}
	}

	// =========================================================
	// BUSCAR
	// =========================================================

	private void buscar() {

		String busca =
				txtBusca.getText().trim();

		if (busca.isEmpty()) {

			listar();

			return;
		}

		try {

			List<Animal> lista =
					dao.buscarPorNome(busca);

			preencherTabela(lista);

		} catch (SQLException ex) {

			erro(
					"Erro ao buscar",
					ex
			);
		}
	}

	// =========================================================
	// PREENCHER TABELA
	// =========================================================

	private void preencherTabela(
			List<Animal> lista
	) {

		modelo.setRowCount(0);

		for (Animal e : lista) {

			modelo.addRow(
					new Object[] {
							e.getId(),
							e.getNome(),
							e.getEspecie(),
							e.getTamanho(),
							e.getPeso(),
							e.getNascimento(),
							e.getDieta()
					}
			);
		}

		lblStatus.setText(
				lista.size()
						+ " Animais na tabela."
		);
	}

	

	// =========================================================
	// LIMPAR
	// =========================================================

	private void limpar() {

	    idSelecionado = 0;

	    tabela.clearSelection();

	    lblStatus.setText(
	            "Seleção limpa."
	    );
	}

	// =========================================================
	// ERRO
	// =========================================================

	private void erro(
			String contexto,
			SQLException ex
	) {

		JOptionPane.showMessageDialog(
				this,
				contexto
						+ ": "
						+ ex.getMessage(),
				"Erro",
				JOptionPane.ERROR_MESSAGE
		);

		lblStatus.setText(
				contexto + "."
		);
	}

	// =========================================================
	// CARD - TELA CADASTRAR
	// =========================================================

	private JPanel criarTelaCadastrar() {

		JPanel painel =
				new JPanel(
						new MigLayout("", "[90px][278.00,grow]", "[][][][][][]20px[]")
				);

		JLabel lblNome =
				new JLabel("Nome:");

		txtNomeDireita =
				new JTextField();

		painel.add(
				lblNome, "cell 0 0"
		);

		painel.add(
				txtNomeDireita,
				"cell 1 0,growx"
		);
		
				JLabel lblEspecie =
						new JLabel("Espécie:");
				
						painel.add(
								lblEspecie, "cell 0 1"
						);
				
						txtEspecieDireita =
								new JTextField();
						
								painel.add(
										txtEspecieDireita,
										"cell 1 1,growx"
								);
		
				JLabel lblTamanho =
						new JLabel("Tamanho:");
				
						painel.add(
								lblTamanho, "cell 0 2"
						);
		
				txtTamanhoDireita =
						new JTextField();
				
						painel.add(
								txtTamanhoDireita,
								"cell 1 2,growx"
						);
		
				JLabel lblPeso =
						new JLabel("Peso:");
				
						painel.add(
								lblPeso, "cell 0 3"
						);
				
						txtPesoDireita =
								new JTextField();
						
								painel.add(
										txtPesoDireita,
										"cell 1 3,growx"
								);
		
				JLabel lblNascimento =
						new JLabel("Nascimento:");
				
						painel.add(
								lblNascimento, "cell 0 4"
						);
				
						txtNascimentoDireita =
								new JTextField();
						
								painel.add(
										txtNascimentoDireita,
										"cell 1 4,growx"
								);
		
				JLabel lblDieta =
						new JLabel("Dieta:");
				
						painel.add(
								lblDieta, "cell 0 5"
						);
		
				txtDietaDireita =
						new JTextField();
				
						painel.add(
								txtDietaDireita,
								"cell 1 5,growx"
						);
		
				JButton btnSalvar =
						new JButton("Cadastrar");
				
						painel.add(
							    btnSalvar,
							    "cell 0 6,alignx right"
							);

		btnSalvar.addActionListener(
				new ActionListener() {

					public void actionPerformed(
							ActionEvent e
					) {

						cadastrarPelaTelaDireita();
					}
				}
		);

		return painel;
	}

	
	// =========================================================
	// CARD - TELA EDITAR
	// =========================================================

	private JPanel criarTelaEditar() {

	    JPanel painel =
	            new JPanel(
	                    new MigLayout(
	                            "",
	                            "[90px][278.00,grow]",
	                            "[][][][][][]20px[]"
	                    )
	            );

	    JLabel lblNome =
	            new JLabel("Nome:");

	    txtNomeEditar =
	            new JTextField();

	    painel.add(
	            lblNome,
	            "cell 0 0"
	    );

	    painel.add(
	            txtNomeEditar,
	            "cell 1 0,growx"
	    );


	    JLabel lblEspecie =
	            new JLabel("Espécie:");

	    painel.add(
	            lblEspecie,
	            "cell 0 1"
	    );

	    txtEspecieEditar =
	            new JTextField();

	    painel.add(
	            txtEspecieEditar,
	            "cell 1 1,growx"
	    );


	    JLabel lblTamanho =
	            new JLabel("Tamanho:");

	    painel.add(
	            lblTamanho,
	            "cell 0 2"
	    );

	    txtTamanhoEditar =
	            new JTextField();

	    painel.add(
	            txtTamanhoEditar,
	            "cell 1 2,growx"
	    );


	    JLabel lblPeso =
	            new JLabel("Peso:");

	    painel.add(
	            lblPeso,
	            "cell 0 3"
	    );

	    txtPesoEditar =
	            new JTextField();

	    painel.add(
	            txtPesoEditar,
	            "cell 1 3,growx"
	    );


	    JLabel lblNascimento =
	            new JLabel("Nascimento:");

	    painel.add(
	            lblNascimento,
	            "cell 0 4"
	    );

	    txtNascimentoEditar =
	            new JTextField();

	    painel.add(
	            txtNascimentoEditar,
	            "cell 1 4,growx"
	    );


	    JLabel lblDieta =
	            new JLabel("Dieta:");

	    painel.add(
	            lblDieta,
	            "cell 0 5"
	    );

	    txtDietaEditar =
	            new JTextField();

	    painel.add(
	            txtDietaEditar,
	            "cell 1 5,growx"
	    );


	    JButton btnSalvar =
	            new JButton("Salvar Alterações");

	    painel.add(
	            btnSalvar,
	            "cell 0 6,alignx right"
	    );


	    btnSalvar.addActionListener(
	            new ActionListener() {

	                public void actionPerformed(
	                        ActionEvent e
	                ) {

	                    alterarPelaTelaDireita();
	                }
	            }
	    );

	    return painel;
	}

	// =========================================================
	// CARD - TELA LISTA
	// =========================================================

	private JPanel criarTelaLista() {

		JPanel painel =
				new JPanel(
						new BorderLayout()
				);

		JLabel titulo =
				new JLabel(
						"Lista de Animais"
				);

		titulo.setHorizontalAlignment(
				JLabel.CENTER
		);

		painel.add(
				titulo,
				BorderLayout.NORTH
		);

		JLabel info =
				new JLabel(
						"<html><center>"
						+ "A lista completa está na tabela ao lado.<br>"
						+ "Selecione um animal e use o botão Editar."
						+ "</center></html>"
				);

		info.setHorizontalAlignment(
				JLabel.CENTER
		);

		painel.add(
				info,
				BorderLayout.CENTER
		);

		JButton btnAtualizar =
				new JButton(
						"Atualizar Lista"
				);

		btnAtualizar.addActionListener(
				new ActionListener() {

					public void actionPerformed(
							ActionEvent e
					) {

						listar();
					}
				}
		);

		painel.add(
				btnAtualizar,
				BorderLayout.SOUTH
		);

		return painel;
	}

	// =========================================================
	// CARREGAR DADOS NA TELA EDITAR
	// =========================================================

	

	// =========================================================
	// CADASTRAR PELA TELA DIREITA
	// =========================================================

	private void cadastrarPelaTelaDireita() {

		String nome =
				txtNomeDireita.getText().trim();

		String especie =
				txtEspecieDireita.getText().trim();

		String nascimento =
				txtNascimentoDireita
						.getText()
						.trim();

		String dieta =
				txtDietaDireita
						.getText()
						.trim();

		if (nome.isEmpty()) {

			JOptionPane.showMessageDialog(
					this,
					"Preencha o nome!",
					"Aviso",
					JOptionPane.WARNING_MESSAGE
			);

			txtNomeDireita.requestFocus();

			return;
		}

		double tamanho;

		try {

			tamanho =
					Double.parseDouble(
							txtTamanhoDireita
									.getText()
									.trim()
									.replace(",", ".")
					);

		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(
					this,
					"Tamanho deve ser um número!",
					"Aviso",
					JOptionPane.WARNING_MESSAGE
			);

			txtTamanhoDireita.requestFocus();

			return;
		}

		double peso;

		try {

			peso =
					Double.parseDouble(
							txtPesoDireita
									.getText()
									.trim()
									.replace(",", ".")
					);

		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(
					this,
					"Peso deve ser um número!",
					"Aviso",
					JOptionPane.WARNING_MESSAGE
			);

			txtPesoDireita.requestFocus();

			return;
		}

		Animal animal =
				new Animal(
						nome,
						especie,
						tamanho,
						peso,
						nascimento,
						dieta
				);

		try {

			dao.inserir(animal);

			JOptionPane.showMessageDialog(
					this,
					"Animal cadastrado com o id "
							+ animal.getId()
							+ "."
			);

			limparTelaCadastrar();

			listar();

			cardLayout.show(
					painelCards,
					"LISTA"
			);

		} catch (SQLException ex) {

			erro(
					"Erro ao cadastrar",
					ex
			);
		}
	}

	// =========================================================
	// ALTERAR PELA TELA DIREITA
	// =========================================================

	private void alterarPelaTelaDireita() {

		if (idSelecionado == 0) {

			JOptionPane.showMessageDialog(
					this,
					"Selecione um animal na tabela primeiro.",
					"Aviso",
					JOptionPane.WARNING_MESSAGE
			);

			return;
		}

		String nome =
				txtNomeEditar.getText().trim();

		String especie =
				txtEspecieEditar
						.getText()
						.trim();

		String nascimento =
				txtNascimentoEditar
						.getText()
						.trim();

		String dieta =
				txtDietaEditar
						.getText()
						.trim();

		if (nome.isEmpty()) {

			JOptionPane.showMessageDialog(
					this,
					"Preencha o nome!",
					"Aviso",
					JOptionPane.WARNING_MESSAGE
			);

			txtNomeEditar.requestFocus();

			return;
		}

		double tamanho;

		try {

			tamanho =
					Double.parseDouble(
							txtTamanhoEditar
									.getText()
									.trim()
									.replace(",", ".")
					);

		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(
					this,
					"Tamanho deve ser um número!",
					"Aviso",
					JOptionPane.WARNING_MESSAGE
			);

			txtTamanhoEditar.requestFocus();

			return;
		}

		double peso;

		try {

			peso =
					Double.parseDouble(
							txtPesoEditar
									.getText()
									.trim()
									.replace(",", ".")
					);

		} catch (NumberFormatException ex) {

			JOptionPane.showMessageDialog(
					this,
					"Peso deve ser um número!",
					"Aviso",
					JOptionPane.WARNING_MESSAGE
			);

			txtPesoEditar.requestFocus();

			return;
		}

		Animal animal =
				new Animal(
						nome,
						especie,
						tamanho,
						peso,
						nascimento,
						dieta
				);

		animal.setId(
				idSelecionado
		);

		try {

			dao.alterar(animal);

			JOptionPane.showMessageDialog(
					this,
					"Animal alterado com sucesso."
			);

			listar();

			limpar();

			cardLayout.show(
					painelCards,
					"LISTA"
			);

		} catch (SQLException ex) {

			erro(
					"Erro ao alterar",
					ex
			);
		}
	}

	// =========================================================
	// LIMPAR TELA DE CADASTRO DIREITA
	// =========================================================

	private void limparTelaCadastrar() {

		txtNomeDireita.setText("");
		txtEspecieDireita.setText("");
		txtTamanhoDireita.setText("");
		txtPesoDireita.setText("");
		txtNascimentoDireita.setText("");
		txtDietaDireita.setText("");

		txtNomeDireita.requestFocus();
	}
}
