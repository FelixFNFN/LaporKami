<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form method="POST" action="http://localhost:8000/api/user/insert">
        <h1>Form Insert</h1><br>
        email:<input type="text" name="email"><br>
        nama lengkap:<input type="text" name="nama"><br>
        nomor telpon:<input type="text" name="noTelp"><br>
        password:<input type="text" name="password"><br>
        <input type="submit" name="submit" value="submit">
    </form>
    <form method="POST" action="http://localhost:8000/api/user/login">
        <h1>Form Login</h1><br>
        email:<input type="text" name="email"><br>
        password:<input type="text" name="password"><br>
        <input type="submit" name="submit" value="submit">
    </form>
    <form method="POST" action="http://localhost:8000/api/user/delete">
        <h1>Form Delete</h1><br>
        NRP:<input type="text" name="nrp"><br>
        <input type="submit" name="submit" value="submit">
    </form>
</body>
</html>
