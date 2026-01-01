package utils;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.style.PieStyler;
import java.io.IOException;

public class ChartGenerator {

	public static void generatePieChart(int passed, int failed, int skipped) {

		PieChart chart = new PieChartBuilder().width(800).height(600).title("Test Execution Summary").build();

		chart.getStyler().setLegendVisible(true);

		chart.getStyler().setAnnotationType(PieStyler.AnnotationType.LabelAndPercentage);

		chart.addSeries("Passed", passed);

		chart.addSeries("Failed", failed);

		chart.addSeries("Skipped", skipped);

		try {
			BitmapEncoder.saveBitmap(chart, "test-output/ExtentReports/TestSummary", BitmapEncoder.BitmapFormat.PNG);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

}