/**MarvelGraph
 **MarvelGraphMain.java
 * Nov 5, 2013:11:45:20 AM
 */

package com.frankdye.MarvelGraph;

//Includes

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Stroke;

import javax.swing.JFrame;




import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.visualization.BasicVisualizationServer;

/**
 * @author Frank Dye
 * 
 */

public class MarvelGraphMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		SimpleGraphView sgv = new SimpleGraphView(); // We create our graph in
														// here
		// The Layout<V, E> is parameterized by the vertex and edge types
		Layout<String, String> layout = new CircleLayout(sgv.g);
		layout.setSize(new Dimension(1200, 1200)); // sets the initial size of
													// the // space
		// The BasicVisualizationServer<V,E> is parameterized by the edge types
		/*
		 * BasicVisualizationServer<String, String> vv = new
		 * BasicVisualizationServer<String, String>( layout);
		 */
		VisualizationViewer<String, String> vv = new VisualizationViewer<String, String>(
				layout);
		vv.setPreferredSize(new Dimension(1200, 1200)); // Sets the viewing area
														// size
		// Show vertex and edge labels
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		// Create a graph mouse and add it to the visualization component
		DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
		gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		vv.setGraphMouse(gm);
		vv.setGraphLayout(layout);

		/*Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
			public Paint transform(Integer i) {
				return Color.GREEN;
			}
		};*/
		// Set up a new stroke Transformer for the edges
		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_MITER, 10.0f, dash, 0.0f);
		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
			public Stroke transform(String s) {
				return edgeStroke;
			}
		};
		//vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setEdgeStrokeTransformer(edgeStrokeTransformer);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		JFrame frame = new JFrame("Simple Graph View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);

	}
}
