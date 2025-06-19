<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>新規登録</title>
    <style>
        body {
            background-color: #d3e6e9;
            font-family: Arial, sans-serif;
            text-align: center;
        }

        .form-container {
            margin: 0 auto;
            width: 400px;
            padding-top: 50px;
        }

        h2 {
            font-weight: bold;
            margin-bottom: 30px;
        }

        table {
            margin: 0 auto;
        }

        td {
            padding: 10px;
        }

        input[type="text"],
        input[type="password"],
        input[type="number"],
        select {
            width: 250px;
            height: 30px;
            font-size: 14px;
        }

        input[type="submit"] {
            width: 100px;
            height: 40px;
            font-size: 16px;
            margin-top: 20px;
        }

        .link {
            margin-top: 20px;
            display: block;
        }
    </style>
</head>
<body>
<%String message = (String)request.getAttribute("message"); %>
    <div class="form-container">
        <h2>新規登録</h2>
       	<span style="color:blue;"><%=message != null ? message:"" %></span>
        <form action="/Suwazon_zuichan/login" method="post">
            <input type="hidden" name="action" value="registration">
            <table>
                <tr>
                    <td>ユーザーID</td>
                    <td><input type="text" name="user_id" required /></td>
                </tr>
                <tr>
                    <td>ユーザー名</td>
                    <td><input type="text" name="user_name" required /></td>
                </tr>
                <tr>
                    <td>性別</td>
                    <td>
                        <select name="gender" required>
                            <option value="">選択してください</option>
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>年齢</td>
                    <td><input type="number" name="age" min="0" required /></td>
                </tr>
                <tr>
                    <td>パスワード</td>
                    <td><input type="password" name="password" required /></td>
                </tr>
            </table>
            <input type="submit" value="登録" />
        </form>
        <a href="login.jsp" class="link">ログインページへ</a>
    </div>
</body>
</html>
