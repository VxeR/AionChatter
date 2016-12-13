import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Listener;

public class Loogr {
	public Loogr() {
	}

	protected Shell shell;
	private Text chatLog;
	String timestamp = null;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Loogr window = new Loogr();
			window.open();
			//window.log();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getTimestamp(String l){
		if (l == "")
			timestamp = "";
		else
			timestamp = l;
	}
	
	public void readAppend(String line){
		getTimestamp(line);
		String key1  = "Triiiish";
		String key2  = "[charname:";
		String key3  = "logged in";
		String key4  = "logged out";
		
		if (line.toLowerCase().indexOf(key1.toLowerCase()) != -1 || line.toLowerCase().indexOf(key2.toLowerCase()) != -1 || line.toLowerCase().indexOf(key3.toLowerCase()) != -1 || line.toLowerCase().indexOf(key4.toLowerCase()) != -1)
			chatLog.append(line + "\n");
	}
	
	public void log(){
		String filePath = "C:\\Users\\Pavel\\AppData\\Roaming\\Skype\\My Skype Received Files\\Chat.log";
		
		try {
			FileInputStream fstream = new FileInputStream("C:\\Users\\Pavel\\workspace\\AionChatter\\src\\test.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String line;
			
			while (true) {
				line = br.readLine();
				if (line == null) {
					Thread.sleep(500);
            	} else {
            		readAppend(line);
            	}
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
		
		
		/*
		try {
			File file = new File("C:\\Users\\Pavel\\workspace\\AionChatter\\src\\test.txt");
			//File file = new File(filePath);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			Scanner scanner = new Scanner(file);
				

		    //now read the file line by line...
		    boolean found = false;
		    
		    if(timestamp == null) {
		    	while ((line = bufferedReader.readLine()) != null) {
		    		//line = scanner.nextLine();
		    		readAppend(line);
		    	}
		    }else {
		    	 while ((line = bufferedReader.readLine()) != null) {
		    		 //line = scanner.nextLine();
		    		 if(line == timestamp && !found) {
		    			 found = true;
		    			 while(true){
			            	line = scanner.nextLine();
			            	if(line != timestamp)
			            		break;
			            }
		    		 }
		    		 if(found)
		    			 readAppend(line);
		    	 }
		    }   
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
	

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			log();
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		// window
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("Aion Chat Logger");
		
		// text area
		chatLog = new Text(shell, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		chatLog.setEditable(true);
		chatLog.setText("[timestamp] name: message \n");
		//chatLog.setText("");
		chatLog.setLayoutData(new GridData(GridData.FILL_BOTH));
		chatLog.setBounds(0, 0, 434, 261);

	}
}
