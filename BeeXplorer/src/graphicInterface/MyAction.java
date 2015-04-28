package graphicInterface;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFileChooser;

import server.ServerComunication;
import editor.CreateMap;

public class MyAction extends AbstractAction {
	String text;
	public MyAction (String text, Icon imagen, String description, Integer nemonic){
		super(text,imagen);
		this.text = text;
		this.putValue( Action.SHORT_DESCRIPTION ,description);
		this.putValue(Action.MNEMONIC_KEY, nemonic);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		switch(arg0.getActionCommand()){
		case "Create map":
			CreateDialog createDialogThread = new CreateDialog();
			createDialogThread.run();
			break;
		case "Edit map":
			CreateMap createMapThread = new CreateMap();
			createMapThread.run();
			break;
		case "Show":
			ShowDialog showDialogThread = new ShowDialog();
			showDialogThread.run();
			break;
		case "Share":
			ServerComunication serverComunicationThread = new ServerComunication();
			serverComunicationThread.run();
			break;
		case "Manual control":
			Manual manualThread = new Manual();
			manualThread.run();
			break;
		case "Delete":
			JFileChooser j= new JFileChooser();
	        j.showOpenDialog(j);
	        String fileName = j.getSelectedFile().getAbsolutePath();
	        File file = new File(fileName);
	        file.delete();
			break;
		case "Exit":
			System.exit(-1);
			break;
		}
		
		
	}
}
