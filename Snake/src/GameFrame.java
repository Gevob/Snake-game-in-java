import javax.swing.JFrame;//finestra con bordo



public class GameFrame extends JFrame {

	 GameFrame() {
		this.add(new GamePanel());
		this.setTitle("SNAKE");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// alla x chiudo tutto
		this.setResizable(false);//impedisce di modificare le dimensioni della finestra
		this.pack();//assegna le dimensioni al frame, crea una finestra di dimensioni tali da 
		           // contenere tutto
		this.setVisible(true);
		this.setLocationRelativeTo(null);//crea al centro
	}

}
