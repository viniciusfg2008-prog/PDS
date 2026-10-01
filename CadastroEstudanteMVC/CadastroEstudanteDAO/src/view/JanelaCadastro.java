package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

/**
 * VIEW - a MESMA janela de antes, nas mesmas posicoes.
 *
 * O que saiu daqui: - os tres addActionListener (quem escuta agora e o
 * Controller); - o JOptionPane que montava a mensagem (quem decide o texto e o
 * Controller).
 *
 * O que entrou: - getters dos TRES BOTOES (antes so os campos tinham); -
 * metodos mostrarMensagem, mostrarErro, limparCampos e fechar.
 *
 * Confira com Ctrl+F: nao ha "addActionListener" e nao ha "if" nesta classe.
 */
public class JanelaCadastro extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEspecie;
	private JTextField txtTamanho;
	private JTextField txtPeso;
	private JTextField txtNascimento;
	private JTextField txtDieta;
// NOVO: os botoes viraram atributos. Antes eram variaveis locais do
// construtor, porque so o proprio construtor precisava deles para
// pendurar o ouvinte. Agora quem pendura o ouvinte e o Controller,
// e para isso ele precisa alcanca-los.
	private JButton btnCadastrar;
	private JButton btnLimpar;
	private JButton btnFechar;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public JanelaCadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[77px][96px][105px]", "[22px][22px][22px][20px][16.00px][7.00px]"));
		JLabel lblNome = new JLabel("Nome");
		contentPane.add(lblNome, "cell 0 0,growx,aligny center");
		
		txtNome = new JTextField();
		contentPane.add(txtNome, "cell 1 0,growx,aligny center");
		txtNome.setColumns(10);
		
// 105 e nao 88: com 88 o texto aparece cortado como "Cada...".
		btnCadastrar = new JButton("Cadastrar");
		contentPane.add(btnCadastrar, "cell 2 0,growx,aligny center");
		JLabel lblEspecie = new JLabel("Especie");
		contentPane.add(lblEspecie, "cell 0 1,growx,aligny center");
		txtEspecie = new JTextField();
		contentPane.add(txtEspecie, "cell 1 1,growx,aligny center");
		txtEspecie.setColumns(10);
		btnLimpar = new JButton("Limpar");
		contentPane.add(btnLimpar, "cell 2 1,growx,aligny center");
		JLabel lblTamanho = new JLabel("Tamanho");
		contentPane.add(lblTamanho, "cell 0 2,growx,aligny center");
		txtTamanho = new JTextField();
		contentPane.add(txtTamanho, "cell 1 2,growx,aligny center");
		txtTamanho.setColumns(10);
		btnFechar = new JButton("Fechar");
		contentPane.add(btnFechar, "cell 2 2,growx,aligny center");
		
		JLabel lblPeso = new JLabel("Peso");
		contentPane.add(lblPeso, "cell 0 3,growx,aligny center");
		
		JLabel lblNascimento = new JLabel("Nascimento");
		contentPane.add(lblNascimento, "cell 0 4,growx,aligny top");
		
		JLabel lblDieta = new JLabel("Dieta");
		contentPane.add(lblDieta, "cell 0 5,growx,aligny top");
		
		txtPeso = new JTextField();
		contentPane.add(txtPeso, "cell 1 3,growx,aligny top");
		txtPeso.setColumns(10);
		
		txtNascimento = new JTextField();
		contentPane.add(txtNascimento, "cell 1 4,growx,aligny bottom");
		txtNascimento.setColumns(10);
		
		txtDieta = new JTextField();
		contentPane.add(txtDieta, "cell 1 5,growx,aligny bottom");
		txtDieta.setColumns(10);
	}

// ---- Grupo 1: entregar componentes ao Controller ------------------
	public JTextField getTxtNome() {
		return txtNome;
	}

// ATENCAO: na versao antiga este metodo chamava-se getTextMatricula,
// sem o "tx". Padronizado para getTxtMatricula, igual aos outros dois.
	public JTextField getTxtEspecie() {
		return txtEspecie;
	}

	public JTextField getTxtTamanho() {
		return txtTamanho;
	}
	
	public JTextField getTxtPeso() {
		return txtPeso;
	}
	
	public JTextField getTxtNascimento() {
		return txtNascimento;
	}
	
	public JTextField getTxtDieta() {
		return txtDieta;
	}

	public JButton getBtnCadastrar() {

		return btnCadastrar;
	}

	public JButton getBtnLimpar() {
		return btnLimpar;
	}

	public JButton getBtnFechar() {
		return btnFechar;
	}

	// ---- Grupo 2: capacidades da propria tela -------------------------
	// Recebem o texto PRONTO. A View exibe; nao escolhe o que dizer.
	public void mostrarMensagem(String texto) {
		JOptionPane.showMessageDialog(this, texto);
	}

	public void mostrarErro(String texto) {
		JOptionPane.showMessageDialog(this, texto, "Atencao", JOptionPane.WARNING_MESSAGE);
	}

	public void limparCampos() {
		txtNome.setText("");
		txtEspecie.setText("");
		txtTamanho.setText("");
		txtPeso.setText("");
		txtNascimento.setText("");
		txtDieta.setText("");
		txtNome.requestFocus();
	}

	public void fechar() {
		dispose();
	}
}