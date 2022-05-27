package utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileManager
{

	/**
	 * Choose a file using the system's file explorer.
	 * @param fileType What type of file to read (Image, PDF, Any, etc...)
	 * @return A File object representing the chosen file.
	 */
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

	/**
	 * Read the file and return its data as a bytes array.
	 * @param file File to read.
	 * @return Byte array containing the file's data.
	 */
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

	/**
	 * Choose an image file using the system file explorer and return it as a bytes
	 * array.
	 */

	public static byte[] chooseImage()
	{
		return readFile(chooseFile(FileType.Image));
	}

	/**
	 * Choose a PDF file using the system file explorer and return it as a bytes
	 * array.
	 */
	public static byte[] choosePDFFile()
	{
		return readFile(chooseFile(FileType.PDF));
	}

	/**
	 * Convert a byte array to a javafx.scene.image.Image object.
	 * 
	 * @param imageBytes Bytes of the image.
	 * @return Image generated from the bytes.
	 */
	public static Image bytesToImage(byte[] imageBytes)
	{
		if(imageBytes == null) return null;
		ByteArrayInputStream byteInputStream = new ByteArrayInputStream(imageBytes);
		Image image = new Image(byteInputStream);
		return image;
	}
}
