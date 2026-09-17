package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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
		contentPane.setLayout(null);
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 21, 77, 14);
		contentPane.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(110, 18, 96, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
// 105 e nao 88: com 88 o texto aparece cortado como "Cada...".
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(238, 17, 105, 22);
		contentPane.add(btnCadastrar);
		JLabel lblEspecie = new JLabel("Especie");
		lblEspecie.setBounds(10, 60, 76, 14);
		contentPane.add(lblEspecie);
		txtEspecie = new JTextField();
		txtEspecie.setBounds(110, 57, 96, 20);
		contentPane.add(txtEspecie);
		txtEspecie.setColumns(10);
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(238, 56, 105, 22);
		contentPane.add(btnLimpar);
		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setBounds(10, 100, 77, 14);
		contentPane.add(lblTamanho);
		txtTamanho = new JTextField();
		txtTamanho.setBounds(110, 97, 96, 20);
		contentPane.add(txtTamanho);
		txtTamanho.setColumns(10);
		btnFechar = new JButton("Fechar");
		btnFechar.setBounds(238, 96, 105, 22);
		contentPane.add(btnFechar);
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setBounds(10, 131, 77, 14);
		contentPane.add(lblPeso);
		
		JLabel lblNascimento = new JLabel("Nascimento");
		lblNascimento.setBounds(10, 156, 77, 14);
		contentPane.add(lblNascimento);
		
		JLabel lblDieta = new JLabel("Dieta");
		lblDieta.setBounds(10, 188, 77, 14);
		contentPane.add(lblDieta);
		
		txtPeso = new JTextField();
		txtPeso.setBounds(110, 128, 96, 20);
		contentPane.add(txtPeso);
		txtPeso.setColumns(10);
		
		txtNascimento = new JTextField();
		txtNascimento.setBounds(110, 153, 96, 20);
		contentPane.add(txtNascimento);
		txtNascimento.setColumns(10);
		
		txtDieta = new JTextField();
		txtDieta.setBounds(110, 185, 96, 20);
		contentPane.add(txtDieta);
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