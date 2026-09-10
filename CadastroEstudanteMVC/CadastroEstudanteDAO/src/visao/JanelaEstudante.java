package visao;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
import java.util.List;
import dao.AnimalDAO;
import modelo.Animal;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;

public class JanelaEstudante extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEspecie;
	private JTextField txtTamanho;
	private JTextField txtBusca;
	private JTable tabela;
	private JLabel lblStatus;
	

	/**
	 * 
	 * Launch the application.
	 * 
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaEstudante frame = new JanelaEstudante();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();

				}

			}

		});

	}

	/**
	 * 
	 * Create the frame.
	 * 
	 */

	public JanelaEstudante() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 468, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[163px,grow][103px,grow][179px,grow]", "[196px,grow][23px,center][20px,center][282px,grow][14px,grow]"));
		lblStatus = new JLabel("Mensagens status");
		contentPane.add(lblStatus, "cell 0 4,growx,aligny top");
		
		JButton btnListar = new JButton("Listar Todos");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtBusca.setText("");
				listar();

			}

		});
		contentPane.add(btnListar, "cell 0 1,alignx right,aligny top");

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();

			}

		});
		contentPane.add(btnBuscar, "cell 2 1,alignx left,aligny top");
		tabela = new JTable();

		JScrollPane scrollPane = new JScrollPane(tabela);
		contentPane.add(scrollPane, "cell 0 3 3 1,grow");

		modelo = new DefaultTableModel(new String[] { "ID", "Nome", "Especie", "Tamanho", "Peso", "Nascimento", "Dieta" }, 0);
		tabela.setModel(modelo);
		tabela.setRowHeight(22);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, "cell 0 2 3 1,grow");
				panel.setLayout(new GridLayout(0, 4, 0, 0));
		
				JButton btnCadastrar = new JButton("Cadastrar");
				panel.add(btnCadastrar);
				
				JButton btnAlterar = new JButton("Alterar");
				panel.add(btnAlterar);
				
						JButton btnExcluir = new JButton("Excluir");
						panel.add(btnExcluir);
						
						
								JButton btnLimpar = new JButton("Limpar");
								panel.add(btnLimpar);
								
								JPanel panel_1 = new JPanel();
								contentPane.add(panel_1, "cell 0 0 3 1,growx,aligny top");
								panel_1.setLayout(new MigLayout("", "[][133.00,grow]", "[][][][][][][]"));
												
														JLabel lblNewLabel = new JLabel("Nome:");
														panel_1.add(lblNewLabel, "cell 0 0");
												
														txtNome = new JTextField();
														panel_1.add(txtNome, "cell 1 0,growx");
														txtNome.setColumns(10);
														
														JLabel lblNewLabel_1 = new JLabel("Especie:");
														panel_1.add(lblNewLabel_1, "cell 0 1");
														
																txtEspecie = new JTextField();
																panel_1.add(txtEspecie, "cell 1 1,growx");
																txtEspecie.setColumns(10);
																
																		JLabel lblNewLabel_2 = new JLabel("Tamanho:");
																		panel_1.add(lblNewLabel_2, "cell 0 2");
																		
																				txtTamanho = new JTextField();
																				panel_1.add(txtTamanho, "cell 1 2,growx");
																				txtTamanho.setColumns(10);
																				
																				JLabel lblNewLabel_2_1 = new JLabel("Peso:");
																				panel_1.add(lblNewLabel_2_1, "cell 0 3");
																				
																				txtPeso = new JTextField();
																				panel_1.add(txtPeso, "cell 1 3,growx");
																				txtPeso.setColumns(10);
																						
																						JLabel lblNewLabel_2_1_1 = new JLabel("Nascimento:");
																						panel_1.add(lblNewLabel_2_1_1, "flowy,cell 0 4");
																						
																						txtNascimento = new JTextField();
																						txtNascimento.setColumns(10);
																						panel_1.add(txtNascimento, "cell 1 4,growx");
																								
																								JLabel lblNewLabel_2_1_2 = new JLabel("Dieta:");
																								panel_1.add(lblNewLabel_2_1_2, "cell 0 5");
																										
																										txtDieta = new JTextField();
																										txtDieta.setColumns(10);
																										panel_1.add(txtDieta, "cell 1 5,growx");
																								
																										JLabel lblNewLabel_3 = new JLabel("Buscar:");
																										panel_1.add(lblNewLabel_3, "flowy,cell 0 6");
																								
																										txtBusca = new JTextField();
																										panel_1.add(txtBusca, "cell 1 6,growx");
																										txtBusca.setColumns(10);
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
				
						btnAlterar.addActionListener(new ActionListener() {
						    public void actionPerformed(ActionEvent e) {
						        alterar();
						    }
						});
				btnCadastrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						txtBusca.setText("");
						cadastrar();
					}

				});

		// Impede a edicao direta na celula: alterar passa pelo formulario.

		tabela.setDefaultEditor(Object.class, null);
		listar(); // a tabela ja abre preenchida
		tabela.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					carregarSelecionado();

				}

			}

		});

	}

	private void carregarSelecionado() {
		int linha = tabela.getSelectedRow();
		if (linha < 0)
			return; // -1 = nenhuma linha selecionada

		idSelecionado = (int) modelo.getValueAt(linha, 0);
		txtNome.setText(String.valueOf(modelo.getValueAt(linha, 1)));
		txtEspecie.setText(String.valueOf(modelo.getValueAt(linha, 2)));
		txtTamanho.setText(String.valueOf(modelo.getValueAt(linha, 3)));
		txtPeso.setText(String.valueOf(modelo.getValueAt(linha, 4)));
		txtNascimento.setText(String.valueOf(modelo.getValueAt(linha, 5)));
		txtDieta.setText(String.valueOf(modelo.getValueAt(linha, 6)));
		lblStatus.setText("Editando o animal de id " + idSelecionado
				+ ". Altere os campos e clique em Alterar.");

	}

	private void alterar() {
		if (idSelecionado == 0) {
			JOptionPane.showMessageDialog(this, "Selecione primeiro uma linha da tabela.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;

		}

		Animal e = lerFormulario();
		if (e == null)
			return;

		// O id vem da SELECAO, nao do que esta digitado.

		e.setId(idSelecionado);
		try {

			dao.alterar(e);
			JOptionPane.showMessageDialog(this, "Animal alterado.");
			limpar();
			listar();
		} catch (SQLException ex) {
			erro("Erro ao alterar", ex);

		}

	}

	private void excluir() {
		if (idSelecionado == 0) {
			JOptionPane.showMessageDialog(this, "Selecione primeiro uma linha da tabela.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			return;

		}

		int opcao = JOptionPane.showConfirmDialog(this,
				"Excluir o animal " + txtNome.getText() + "?",
				"Confirmacao", JOptionPane.YES_NO_OPTION);
		if (opcao != JOptionPane.YES_OPTION)
			return;
		
		try {
			dao.excluir(idSelecionado);
			JOptionPane.showMessageDialog(this, "Animal excluido.");
			limpar();
			listar();

		} catch (SQLException ex) {
			erro("Erro ao excluir", ex);

		}

	}
	public JTextField getTxtNome() {
		return txtNome;
	}
	public JTextField getTxtCurso() {
		return txtEspecie;
	}
	public JTextField getTxtNota() {
		return txtTamanho;
	}
	public JTextField getTxtPeso() {
		return txtPeso;
	}
	public JTextField getTextNascimento() {
		return txtNascimento;
	}
	public JTextField getTextDieta() {
		return txtDieta;
	}
	public JTextField getTxtBusca() {
		return txtBusca;
	}
	public JTable getTabela() {
		return tabela;
	}
	public JLabel getLblStatus() {
		return lblStatus;
	}

	private DefaultTableModel modelo;
	// Ponte com o banco: um unico objeto serve a janela inteira.
	private final AnimalDAO dao = new AnimalDAO();
	// Id do estudante selecionado na tabela. Zero = nenhum selecionado.
	private int idSelecionado = 0;
	private JTextField txtPeso;
	private JTextField txtNascimento;
	private JTextField txtDieta;
	private void listar() {

		try {
			preencherTabela(dao.listar());
		} catch (SQLException ex) {
			erro("Erro ao listar", ex);
		}

	}

	private void buscar() {
		try {
			preencherTabela(dao.buscarPorNome(txtBusca.getText().trim()));
		} catch (SQLException ex) {
			erro("Erro ao buscar", ex);

		}

	}

	private void preencherTabela(List<Animal> lista) {
		modelo.setRowCount(0); 
		for (Animal e : lista) {
			modelo.addRow(new Object[] {
					e.getId(), e.getNome(), e.getEspecie(), e.getTamanho(), e.getPeso(), e.getNascimento(), e.getDieta() });

		}

		lblStatus.setText("0 animais na tabela.");

	}

	private Animal lerFormulario() {
		String nome = txtNome.getText().trim();
		String curso = txtEspecie.getText().trim();
		if (nome.isEmpty()) {

			JOptionPane.showMessageDialog(this, "Preencha o nome!",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			txtNome.requestFocus();
			return null;

		}

		double nota;
		try {

			nota = Double.parseDouble(txtTamanho.getText().trim().replace(",", "."));
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Nota deve ser um numero!",
					"Aviso", JOptionPane.WARNING_MESSAGE);

			txtTamanho.requestFocus();
			return null;

		}

		if (nota < 0 || nota > 10) {
			JOptionPane.showMessageDialog(this, "A nota deve estar entre 0 e 10.",
					"Aviso", JOptionPane.WARNING_MESSAGE);
			txtTamanho.requestFocus();
			return null;

		}

		return new Animal(nome, especie, tamanho, peso, nascimento, dieta);
	}

	private void cadastrar() {
		Animal e = lerFormulario();
		if (e == null)
			return; // invalido: a mensagem ja apareceu
		
		try {
			dao.inserir(e);
			JOptionPane.showMessageDialog(this,
					"Animal cadastrado com o id " + e.getId() + ".");

			limpar();
			listar();
		} catch (SQLException ex) {
			erro("Erro ao cadastrar", ex);

		}

	}

	private void limpar() {
		idSelecionado = 0;
		txtNome.setText("");
		txtEspecie.setText("");
		txtTamanho.setText("");
		tabela.clearSelection();
		txtNome.requestFocus();
		lblStatus.setText("Formulario limpo.");

	}

	private void erro(String contexto, SQLException ex) {
		JOptionPane.showMessageDialog(this,
				contexto + ": " + ex.getMessage(),
				"Erro", JOptionPane.ERROR_MESSAGE);
		lblStatus.setText(contexto + ".");

	}
}