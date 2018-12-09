package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class Tapahtumankuuntelija implements EventHandler {
	private TextField tuloskentta;
	private TextField syotekentta;
	private Button nollaa;
	private Button undo;
	private Sovelluslogiikka sovellus;

	private interface Command {
		void run(int val);
	}

	private Map<Button, Command> commands = new HashMap<>();

	public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.nollaa = nollaa;
		this.undo = undo;
		this.sovellus = new Sovelluslogiikka();
		this.commands.put(plus, sovellus::plus);
		this.commands.put(miinus, sovellus::miinus);
		this.commands.put(nollaa, sovellus::nollaa);
		this.commands.put(undo, sovellus::peru);
	}

	@Override
	public void handle(Event event) {
		int val = 0;

		try {
			val = Integer.parseInt(syotekentta.getText());
		} catch (Exception ignored) {}

		this.commands.get(event.getTarget()).run(val);

		int laskunTulos = sovellus.tulos();

		syotekentta.setText("");
		tuloskentta.setText("" + laskunTulos);

		if (laskunTulos == 0) {
			nollaa.disableProperty().set(true);
		} else {
			nollaa.disableProperty().set(false);
		}
		undo.disableProperty().set(false);
	}

}
