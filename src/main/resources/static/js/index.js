class ContinuousForceDirectedLayout extends go.ForceDirectedLayout {
	isFixed(v) {
		return v.node.isSelected;
	}

	doLayout(coll) {
		if (!this._isObserving) {
			this._isObserving = true;
			this.diagram.addModelChangedListener(e => {

				if (e.modelChange !== "" || (e.change === go.ChangedEvent.Transaction && e.propertyName === "StartingFirstTransaction")) {
					this.network = null;
				}

			});
		}
		var net = this.network;
		if (net === null) {
			this.network = net = this.makeNetwork(coll);
		} else {
			this.diagram.nodes.each(n => {
				var v = net.findVertex(n);
				if (v !== null) v.bounds = n.actualBounds;
			});
		}

		super.doLayout(coll);
		this.network = net;
	}
}

function init() {
	const $ = go.GraphObject.make;

	myDiagram =
		$(go.Diagram, "myDiagramDiv",
			{
				initialAutoScale: go.Diagram.Uniform,
				contentAlignment: go.Spot.Center,
				layout:
					$(ContinuousForceDirectedLayout,
						{ defaultSpringLength: 0, defaultElectricalCharge: 100 }),

				"SelectionMoved": e => e.diagram.layout.invalidateLayout()
			});

	myDiagram.toolManager.draggingTool.doMouseMove = function() {
		go.DraggingTool.prototype.doMouseMove.call(this);
		if (this.isActive) { this.diagram.layout.invalidateLayout(); }
	}

	myDiagram.nodeTemplate =
		$(go.Node, "Auto",
			$(go.Shape, "Circle",
				{ fill: "White", stroke: "black", spot1: new go.Spot(0, 0, 5, 5), spot2: new go.Spot(1, 1, -5, -5) }),
			$(go.TextBlock,
				{ font: "bold 18pt helvetica, bold arial, sans-serif", textAlign: "center", maxSize: new go.Size(100, NaN) },
				new go.Binding("text", "text"))
		);

	myDiagram.linkTemplate =
		$(go.Link,
			$(go.Shape,
				{ stroke: "black" }),
			$(go.Shape,
				{ toArrow: "standard", stroke: null }),
			$(go.Panel, "Auto",
				$(go.Shape,
					{
						fill: $(go.Brush, "Radial", { 0: "rgb(240, 240, 240)", 0.3: "rgb(240, 240, 240)", 1: "rgba(240, 240, 240, 0)" }),
						stroke: null
					}),
				$(go.TextBlock,
					{
						textAlign: "center",
						font: "16pt helvetica, arial, sans-serif",
						stroke: "#555555",
						margin: 4
					},
					new go.Binding("text", "text"))
			)
		);

	var nodeDataArray = JSON.parse(document.getElementById("input").value);

	var linkDataArray = JSON.parse(document.getElementById("input2").value);
	myDiagram.model = new go.GraphLinksModel(nodeDataArray, linkDataArray);
}

function reload() {
	var text = myDiagram.model.toJson();
	myDiagram.model = go.Model.fromJson(text);
	myDiagram.layout =
		go.GraphObject.make(ContinuousForceDirectedLayout,
			{ defaultSpringLength: 30, defaultElectricalCharge: 100 });
}
window.addEventListener('DOMContentLoaded', init);