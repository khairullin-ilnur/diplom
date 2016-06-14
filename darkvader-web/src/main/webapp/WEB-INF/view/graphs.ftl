<!DOCTYPE html>
<html>
<head lang="ru">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My project</title>

    <link href="/resources/css/bootstrap.css" rel="stylesheet">

    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
        google.charts.load("current", {packages: ['corechart']});
        google.charts.setOnLoadCallback(drawChart);
        function drawChart() {
            var data = google.visualization.arrayToDataTable([
                ["Emotion", "Value", {role: "style"}],
                ["Отвращение", ${answer.questionContempt} , "#225378"],
                ["Страх", ${answer.questionFear} , "#ACF0F2"],
                ["Удивление", ${answer.questionSurprise} , "#1695A3"],
                ["Радость", ${answer.questionEnjoyment} , "color: #EB7F00"],
                ["Интерес", ${answer.questionInterest} , "color: #FF8C00"],
                ["Злость", ${answer.questionAnger} , "color: #FF2D00"],
                ["Стыд", ${answer.questionShame} , "color: #F3FFE2"],
                ["Страдание", ${answer.questionDistress} , "color: #e5e4e2"]
            ]);

            var view = new google.visualization.DataView(data);
            view.setColumns([0, 1,
                {
                    calc: "stringify",
                    sourceColumn: 1,
                    type: "string",
                    role: "annotation"
                },
                2]);

            var options = {
                title: "Численная модель изменения эмоций респондентов",
                width: 830,
                height: 400,
                bar: {groupWidth: "95%"},
                legend: {position: "none"},
            };
            var chart = new google.visualization.ColumnChart(document.getElementById("columnchart_values"));
            chart.draw(view, options);

            var data = google.visualization.arrayToDataTable([
                ['Кол-во просмотров', 'CTR'],
                [${predictionMovie.movieViewCount}/ 3, ((${predictionMovie.movieLikeCount} * 100) /(${predictionMovie.movieViewCount} / 3)
        )],
            [${predictionMovie.movieViewCount}/ 1.5, ((${predictionMovie.movieLikeCount} * 100) /(${predictionMovie.movieViewCount} / 1.5
        ))
        ],
        [${predictionMovie.movieViewCount}, ((${predictionMovie.movieLikeCount} * 100) / (${predictionMovie.movieViewCount})
        )],
        [${predictionMovie.movieViewCount} * 1.5, ((${predictionMovie.movieLikeCount} * 100) / (${predictionMovie.movieViewCount} * 1.5
        ))],
        [${predictionMovie.movieViewCount} * 3, ((${predictionMovie.movieLikeCount} * 100) / (${predictionMovie.movieViewCount} * 3
        ))]
        ])
        ;

        var options = {
            title: 'Прогнозируемый CTR',
            curveType: 'function',
            legend: {position: 'bottom'}
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
        chart.draw(data, options);
        }
    </script>
    <script type="text/javascript" src="/resources/js/vis.js"></script>
    <script type="text/javascript">
        var data = null;
        var graph = null;

        // Called when the Visualization API is loaded.
        function drawVisualization() {
            // create the data table.
            data = new vis.DataSet();

            // create some shortcuts to math functions
            var random = Math.random;
            var pi = Math.PI;


        <#list personPoints as personPoint>
            var xmin = ${personPoint.oldX};
            var ymin = ${personPoint.oldY};
            var zmin = ${personPoint.oldZ};
            var xmax = ${personPoint.newX};
            var ymax = ${personPoint.newY};
            var zmax = ${personPoint.newZ};
            var xstep = (xmax - xmin) / 100;
            var ystep = (ymax - ymin) / 100;
            var zstep = (zmax - zmin) / 100;

            for (var t = xmin, k = ymin, l = zmin, value = 0; value < 100; t += xstep, k += ystep, l += zstep, value += 1) {
                data.add({
                    x: t,
                    y: k,
                    z: l,
                    style: value
                });
            }

            data.add({x: ${personPoint.oldX}, y: ${personPoint.oldY}, z: ${personPoint.oldZ}, style: 0});
            data.add({x: ${personPoint.newX}, y: ${personPoint.newY}, z: ${personPoint.newZ}, style: 100});
        </#list>


            // create the animation data
//            var imax = 100;
//            for (var i = 0; i < imax; i++) {
//                var x = ((random()*18)-9);
//                var y = ((random()*18)-9);
//                var z = ((random()*18)-9);
//
//                data.add({x: x, y: y, z: z, style: 3});
//            }

            // specify options
            var options = {
                width: '600px',
                height: '500px',
                style: 'dot-color',
                showPerspective: true,
                showGrid: true,
                animationInterval: 35, // milliseconds
                animationPreload: false,
                animationAutoStart: true,
                keepAspectRatio: true,
                verticalRatio: 1.0,
                legendLabel: 'Куб эмоций',
                xLabel: 'Серотанин',
                yLabel: 'Дофамин',
                zLabel: 'Норадреналин',
                cameraPosition: {
                    horizontal: 0,
                    vertical: 0.22,
                    distance: 2.1
                }
            };

            // create our graph
            var container = document.getElementById('mygraph');
            graph = new vis.Graph3d(container, data, options);
        }
    </script>
</head>
<body onload="drawVisualization()">

<div class="container" style="margin-top: 50px; margin-bottom: 30px;">
    <h4>Прототип программной платформы по исследованию потребительского поведения человека</h4>
    <hr>
    <h4 style="display: inline-block; margin-right: 15px;">Анализ видео-ролика: ${movie.movieTitle}</h4><a
        href="https://youtu.be/${movie.movieLink}" target="_blank">Ссылка
    на Youtube</a>
    <div class="row">
        <div class="panel panel-default col-md-3 col-xs-12" style="margin: 10px;">
            <div class="panel-body">
                Количество просмотров: ${movie.movieViewCount}
            </div>
        </div>
        <div class="panel panel-default col-md-3 col-xs-12" style="margin: 10px;">
            <div class="panel-body">
                Количество like'ов: ${movie.movieLikeCount}
            </div>
        </div>
        <div class="panel panel-default col-md-3 col-xs-12" style="margin: 10px;">
            <div class="panel-body">
                Количество dislike'ов: ${movie.movieDislikeCount}
            </div>
        </div>
    </div>
    <div class="row">
        <div class="panel panel-default col-md-9 col-xs-12" style="margin: 10px;">
            <div class="panel-body" style="position: relative;">
                <div id="columnchart_values" style="width: 100%; height: 400px;"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="panel panel-default col-md-9 col-xs-12" style="margin: 10px;">
            <div class="panel-body" style="position: relative;">
                <div id="curve_chart" style="width: 100%; height: 400px"></div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="panel panel-default col-md-9 col-xs-12" style="margin: 10px;">
            <div class="panel-body" style="position: relative;">
                <div id="mygraph" class="text-center" style="margin-left: 90px;"></div>
                <div id="info"></div>
            </div>
        </div>
    </div>
</div>
</div>

<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>