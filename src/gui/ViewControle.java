package gui;

import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;

public class ViewControle {

	@FXML
	private RadioButton Masc;

	@FXML
	private RadioButton Femi;

	@FXML
	private Button BtCalculo;

	@FXML
	private Button BtLimp;

	@FXML
	private ToggleGroup Pessoa;

	@FXML
	private TextField txtPeso;

	@FXML
	private TextField txtAltura;

	@FXML
	private Label labelImc;

	@FXML
	private Label labelPesoIdeal;

	@FXML
	private Label labelAviso;

	@FXML
	private ImageView imagemView;

	@FXML
	private void CalculoImc() {
		String texto1 = txtPeso.getText();
		String texto2 = txtAltura.getText();
		if (!(BtLimp.isArmed())) {
			if (texto1.matches(".*[a-zA-Z].*") || texto2.matches(".*[a-zA-Z].*")) {
				Alerts.showAlert("NÃO É POSSÍVEL CALCULAR COM LETRAS.", "DIGITE UM NÚMERO!", null, AlertType.ERROR);
				return;
			}
			if (texto1.matches("0") || texto2.matches("0")) {
				Alerts.showAlert("NÃO É POSSÍVEL CALCULAR COM [0].", "DIGITE UM NÚMERO!", null, AlertType.ERROR);
				return;
			}
			if (texto1.matches(".*[-----].*") || texto2.matches(".*[-----].*")) {
				Alerts.showAlert("NÃO É POSSÍVEL CALCULAR COM NEGATIVOS.", "DIGITE UM NÚMERO POSITIVO!", null,
						AlertType.ERROR);
				return;
			}
			if (texto1.matches(".*[,-,-,].*") || texto2.matches(".*[,-,-,].*")) {
				Alerts.showAlert("NÃO É POSSÍVEL CALCULAR COM VIRGULA.", "USE PONTOS NO LUGAR DE VIRGULA!", null,
						AlertType.ERROR);
				return;
			}
			if (texto1.matches("") && texto2.matches("")) {
				Alerts.showAlert("CAMPOS ALTURA E PESO VAZIOS.", "PREENCHA OS CAMPOS [Altura e Peso]!", null,
						AlertType.ERROR);
				return;
			}
			if (texto1.matches("")) {
				Alerts.showAlert("CAMPO PESO VAZIO.", "PREENCHA O CAMPO [Peso]!", null, AlertType.ERROR);
				return;
			}
			if (texto2.matches("")) {
				Alerts.showAlert("CAMPO ALTURA VAZIO.", "PREENCHA O CAMPO [Altura]!", null, AlertType.ERROR);
				return;
			}
			if (!Femi.isSelected() && !Masc.isSelected()) {
				Alerts.showAlert("CAMPO [Sexo] VAZIO.", "INFORME SUA SEXUALIDADE!", null, AlertType.ERROR);
				return;
			}
		}

		if (BtLimp.isArmed()) {
			txtAltura.setText(String.format(""));
			txtPeso.setText(String.format(""));
			labelImc.setText(String.format("", BtLimp));
			labelPesoIdeal.setText(String.format("", BtLimp));
			labelAviso.setText(String.format("", BtLimp));
			String caminhoDaImagem = "..\\..\\1.png ";
			Image novaImagem = new Image("file:" + caminhoDaImagem);
			imagemView.setImage(novaImagem);
			Masc.setSelected(false);
			Femi.setSelected(false);
		}
		if (BtCalculo.isArmed()) {
			double Peso = Double.parseDouble(txtPeso.getText());
			double Altura = Double.parseDouble(txtAltura.getText());
			double Imc = 0;
			double PesoIdeal = 0;

			Imc = Peso / (Altura * Altura);
			labelImc.setText(String.format("%.2f", Imc));
			Altura = Altura * 100;
			if (Masc.isSelected()) {
				PesoIdeal = 52 + (0.75 * (Altura - 152.4));
			} else if (Femi.isSelected()) {
				PesoIdeal = 52 + (0.67 * (Altura - 152.4));
			}
			labelPesoIdeal.setText(String.format("%.2f", PesoIdeal));
			if (Imc < 17) {
				labelAviso.setText(String.format("Muito abaixo do peso !", Imc));
				Alerts.showAlert("MAGREZA GRAVE!",
						"-INSUFICIÊNCIA CARDIACA GRAVE.\n-ANEMIA GRAVE.\n-ENFRAQUECIMENTO DO SIST.IMUNE.", null,
						AlertType.WARNING);
				if (Femi.isSelected()) {
					String caminhoDaImagem = "..\\..\\6.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
				if (Masc.isSelected()) {
					String caminhoDaImagem = "..\\..\\8.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
			} else if (Imc >= 17 && Imc < 18.5) {
				labelAviso.setText(String.format("Abaixo do peso", Imc));
				Alerts.showAlert("MAGREZA LEVE.", "-PROBLEMAS DE SAÚDE LIGADOS A DESNUTRIÇÃO.", null,
						AlertType.WARNING);
				if (Femi.isSelected()) {
					String caminhoDaImagem = "..\\..\\7.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
				if (Masc.isSelected()) {
					String caminhoDaImagem = "..\\..\\9.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
			} else if (Imc >= 18.5 && Imc <= 24.9) {
				labelAviso.setText("Peso normal");
				Alerts.showAlert("EUTRÓFICO.", "-SAUDÁVEL.", null, AlertType.INFORMATION);
				if (Femi.isSelected()) {
					String caminhoDaImagem = "..\\..\\5.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
				if (Masc.isSelected()) {
					String caminhoDaImagem = "..\\..\\10.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
			} else if (Imc >= 25 && Imc <= 29.9) {
				labelAviso.setText("Acima do peso");
				Alerts.showAlert("SOBREPESO.", "-FADIGA.\n-VARIZES.\n-MÁ CIRCULAÇÃO.", null, AlertType.WARNING);
				if (Femi.isSelected()) {
					String caminhoDaImagem = "..\\..\\Captura de tela 2023-09-23 180636.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
				if (Masc.isSelected()) {
					String caminhoDaImagem = "..\\..\\13.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
			} else if (Imc >= 30 && Imc <= 34.9) {
				labelAviso.setText("Obesidade grau I");
				Alerts.showAlert("OBESIDADE!", "-DIABETES.\n-INFARTO.\n-ANGINA.\n-ATEROSCLEROSE.", null,
						AlertType.WARNING);
				if (Femi.isSelected()) {
					String caminhoDaImagem = "..\\..\\GRAU 1.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
				if (Masc.isSelected()) {
					String caminhoDaImagem = "..\\..\\11.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
			} else if (Imc >= 35 && Imc <= 39.9) {
				labelAviso.setText("Obesidade grau II (Severa)");
				Alerts.showAlert("OBESIDADE SEVERA!", "-FALTA DE AR.\n-APNEIA DO SONO.", null, AlertType.WARNING);
				if (Femi.isSelected()) {
					String caminhoDaImagem = "..\\..\\2.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
				if (Masc.isSelected()) {
					String caminhoDaImagem = "..\\..\\GRAU 2.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
			} else {
				labelAviso.setText("Obesidade grau III (Mórbida)");
				Alerts.showAlert("OBESIDADE MORBIDA!", "-REFLUXO.\n-INFARTO.\n-AVC.\n-DIFICULDADE PARA LOCOMOÇÃO.",
						null, AlertType.WARNING);
				if (Femi.isSelected()) {
					String caminhoDaImagem = "..\\..\\3.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
				if (Masc.isSelected()) {
					String caminhoDaImagem = "..\\..\\12.png";
					Image novaImagem = new Image("file:" + caminhoDaImagem);
					imagemView.setImage(novaImagem);
				}
			}
		}
	}
}