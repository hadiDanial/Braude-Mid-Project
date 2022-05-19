package utility;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManager
{
	public static File chooseFile(FileType fileType)
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		switch (fileType)
		{
		case Any:
		{
			fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt"),
					new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
					new ExtensionFilter("PDF Files", "*.pdf"),
					new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
					new ExtensionFilter("All Files", "*.*"));
			break;
		}
		case PDF:
		{
			fileChooser.getExtensionFilters().add(new ExtensionFilter("PDF Files", "*.pdf"));
			break;
		}
		case Image:
		{
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
			break;
		}
		case Text:
		{
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Text Files", "*.txt"));
			break;
		}
		case Audio:
		{
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"));
		}
		default:
			break;
		}
		File selectedFile = fileChooser.showOpenDialog(null);
		return selectedFile;
	}

	public static byte[] readFile(File file)
	{
		try
		{
			byte[] fileBytes = new byte[(int) file.length()];
			FileInputStream fileInputStream;
			fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
			bufferedInputStream.read(fileBytes, 0, fileBytes.length);
			bufferedInputStream.close();
			return fileBytes;
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static byte[] chooseImage()
	{
		return readFile(chooseFile(FileType.Image));
	}
}
