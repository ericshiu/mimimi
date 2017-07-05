'use strict'

$(function () {

  // Step 3. Create a data object
  var data = {

      labels: ["第0周", "第1周", "第2周", "第3周", "第4周", "第5周", "第6周", "第7周", "第8周", "第9周", "第10周"
      , "第11周", "第12周", "第13周", "第14周", "第15周", "第16周", "第17周", "第18周", "第19周", "第20周"
      , "第21周", "第22周", "第23周", "第24周", "第25周", "第26周", "第27周", "第28周", "第29周", "第30周"
      , "第31周", "第32周", "第33周", "第34周", "第35周", "第36周", "第37周", "第38周", "第39周", "第40周"],
      datasets: [
          {
              label: "The First Dataset",
              fillColor: "rgba(153,0,76,0.2)", // magenta
              strokeColor: "rgba(153,0,76,1)", // magenta
              pointColor: "rgba(153,0,76,1)", // magenta
              pointStrokeColor: "#fff", // white
              pointHighlightFill: "#fff", // white
              pointHighlightStroke: "rgba(153,0,76,1)", // magenta
              data: [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
          },
          {
              label: "The Second dataset",
              fillColor: "rgba(255,0,0,0.9)",
              strokeColor: "rgba(255,0,0,1)",
              pointColor: "rgba(255,0,0,1)",
              pointStrokeColor: "#fff", // white
              pointHighlightFill: "#fff", // white
              pointHighlightStroke: "rgba(76,0,153,1)",
              data: [0,null,null,null,1,null,null,null,1.7,
                      2,null,null,3,null,null,4,null,null,5,null,6,
                      null,7,null,8,null,9,null,10,null,11,
                      null,12,null,13,null,14,null,15,null,16]
          }
          ,
          {
              label: "The Second dataset",
              fillColor: "rgba(255,0,0,0.9)",
              strokeColor: "rgba(255,0,0,1)",
              pointColor: "rgba(255,0,0,1)",
              pointStrokeColor: "#fff", // white
              pointHighlightFill: "#fff", // white
              pointHighlightStroke: "rgba(76,0,153,1)",
              data: [0,null,null,null,null,1,null,null,1.2,
                      1.5,null,null,2,null,2.5,null,null,null,null,4,null,
                      null,null,null,6,null,null,null,null,7.5,null,
                      null,null,null,9,null,null,null,null,null,11]
          }
      ]
  };

  // Step 2. Get the context of the canvas element we want to select
  var ctx = document.getElementById("myChart").getContext("2d");
  var myNewChart = new Chart(ctx).Line(data, {
      ///Boolean - Whether grid lines are shown across the chart
      scaleShowGridLines : true,

      //String - Colour of the grid lines
      scaleGridLineColor : "rgba(0,0,0,.05)",

      //Number - Width of the grid lines
      scaleGridLineWidth : 1,

      //Boolean - Whether to show horizontal lines (except X axis)
      scaleShowHorizontalLines: true,

      //Boolean - Whether to show vertical lines (except Y axis)
      scaleShowVerticalLines: false,

      //Boolean - Whether the line is curved between points
      bezierCurve : true,

      //Number - Tension of the bezier curve between points
      bezierCurveTension : 0.4,

      //Boolean - Whether to show a dot for each point
      pointDot : true,

      //Number - Radius of each point dot in pixels
      pointDotRadius : 4,

      //Number - Pixel width of point dot stroke
      pointDotStrokeWidth : 1,

      //Number - amount extra to add to the radius to cater for hit detection outside the drawn point
      pointHitDetectionRadius : 20,

      //Boolean - Whether to show a stroke for datasets
      datasetStroke : true,

      //Number - Pixel width of dataset stroke
      datasetStrokeWidth : 2,

      //Boolean - Whether to fill the dataset with a colour 被景色
      datasetFill : false,
  });

});