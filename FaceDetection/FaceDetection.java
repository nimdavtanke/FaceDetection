package FaceDetection;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

class DetectFace {
	public void run() {
		System.out.println("\nRunning DetectFace");

		// Create a face detector from the cascade file in the resources
		// directory.
		CascadeClassifier faceDetector = new CascadeClassifier(getClass()
				.getResource("lbpcascade_frontalface.xml").getPath());
		Mat image = Highgui.imread(getClass().getResource("football.png")
				.getPath());

		// Detect faces in the image.
		// MatOfRect is a special container class for Rect.
		MatOfRect faceDetections = new MatOfRect();
		faceDetector.detectMultiScale(image, faceDetections);

		System.out.println(String.format("Detected %s faces",
				faceDetections.toArray().length));

		// Draw a bounding box around each face.
		for (Rect rect : faceDetections.toArray()) {
			Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x
					+ rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
		}

		// Save the visualized detection.
		String filename = "FaceDetection.png";
		System.out.println(String.format("Writing %s", filename));
		Highgui.imwrite(filename, image);
	}
}

public class FaceDetection {
	
	private String copyright;
	private String firstName;
	private String lastName;
	private int year =2013;
	
	public String getInfo (){
		return copyright + " " + firstName + " " + lastName + " " + year;
	}

	public void setInfo (String copyright, String firstName, String lastName){
		this.copyright = copyright;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public static void main(String[] args) {
				
		FaceDetection emp1 = new FaceDetection();
		emp1.setInfo("Copyright", "Зайцев", "Денис");
		
		System.out.println(emp1.getInfo());
		//
		// Запускаем библиотеку
		//
		// System.loadLibrary("opencv_java246");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		new DetectFace().run();
	
	}

}
