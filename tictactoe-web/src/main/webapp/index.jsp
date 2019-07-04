<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Tic-tac-toe</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<div id="m">
    <h2>Registration</h2>
    <form method="POST" action="mainmenu">
        <b>Main information:</b>
        <p>
        <p>Nickname:<br><input type="text" id="nickname" name="nickname" required><br><i id="alert1" class="hiddentext"></i></p>
        <p>
            <input type="submit" value="Продолжить">
            <input type="reset" value="Reset">
        </p>
    </form>
</div>
</body>
</html>