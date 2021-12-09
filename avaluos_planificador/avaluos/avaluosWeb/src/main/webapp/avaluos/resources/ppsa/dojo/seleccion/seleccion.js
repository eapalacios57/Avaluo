dojo.provide("ppsa.dojo.seleccion.seleccion");
dojo.require("esri.tasks.query");
dojo.require("esri.symbol");
dojo.require("esri.tasks.geometry");
dojo.require("esri.geometry");
dojo.require("esri.geometry.Point");
dojo.require("dojo.number");
dojo.require("esri.dijit.Popup");
dojo.require("esri.layers.graphics");
dojo.require("dojo.DeferredList");
dojo.require("esri.SpatialReference");
dojo.declare("ppsa.dojo.seleccion.seleccion", null, {
	
	objectIdPredio : null,
	opcion : null,
	url: null,
	urlIcono : null,	
	urlTemp : null,
	urlTopo: null,
	urlUrba: null,
	geometryService : null,
	objectIdsTopo : null,
	objectIdsUrba : null,
	urlUrbaTopo : null,
	objectIdsUrbaTopo : null,
	extentTopo : null,
	extentUrba : null,
	predioUrbaTopo: null,
	dList: null,
	objectIdPredioRural: null,
	
	obtenerParametros : function(xhr, status, args) 
	{
		showLoading();
		
		objectIdPredio = parseInt(args.objectId);		
		opcion = args.popcion;	
		
		if(opcion == "RURAL")
		{
			objectIdPredioRural = parseInt(args.objectIdPredio);			
		}
		
		if(objectIdPredio != null)
		{
			getParametrosResaltar(args.popcion);
		}
	}
});