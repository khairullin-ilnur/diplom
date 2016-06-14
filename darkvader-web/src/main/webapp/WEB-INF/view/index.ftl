<!DOCTYPE html>
<html>
<head lang="ru">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>My project</title>

    <link href="/resources/css/bootstrap.css" rel="stylesheet">
</head>
<body>

<div class="container" style="margin-top: 50px;">
    <h4>Прототип программной платформы по исследованию потребительского поведения человека</h4>
    <hr>
    <div class="col-lg-6">

        <form class="form-horizontal" method="post" action="/parse">
            <fieldset>
                <legend>Аналитика видео с YouTube</legend>
                <div class="form-group">
                    <label for="inputMovie" class="col-lg-2 control-label">Видео</label>
                    <div class="col-lg-10">
                        <input type="text" required class="form-control" id="inputMovie" name="inputMovie"
                               placeholder="YouTube Movie Id">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputQuiz" class="col-lg-2 control-label">Опрос 1 (CSV)</label>
                    <div class="col-lg-10">
                        <input type="file" required class="form-control" id="inputQuiz1" name="inputQuiz1"
                               placeholder="GForm Id">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputQuiz" class="col-lg-2 control-label">Опрос 2 (CSV)</label>
                    <div class="col-lg-10">
                        <input type="file" required class="form-control" id="inputQuiz2" name="inputQuiz2"
                               placeholder="GForm Id">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-lg-10 col-lg-offset-2">
                        <button type="reset" class="btn btn-default">Очистить</button>
                        <button type="submit" class="btn btn-primary">Отправить</button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>
</div>

<script src="/resources/js/bootstrap.min.js"></script>
</body>
</html>
