// host path regular expression
var pathRegex = new RegExp(/\/[^\/]+$/);
var locationPath = location.pathname.replace(pathRegex, '');


var dojoConfig = {
      parseOnLoad: true,     
      packages: [{
          name: "ppsa",
          location: locationPath + '/ppsa'
        }]
    };

//Variables Globales

var map;
var parametrosResaltarPredio;

