<?php
function dbConnect() {
    $host = "127.0.0.1";
    $user = "postgres";
    $pass = "cabect";
    $db = "tudr";

    // Create connection.
    $link = pg_connect("host=$host dbname=$db user=$user password=$pass");

    // returns the connection as a variable that can be used
    return $link;
}

$conn = dbConnect();
function addUser($conn, $name, $password, $role) {
  // write this
  $userSql = "INSERT INTO users (name, password, role) VALUES($1, $2, $3) RETURNING name, role, id";
  $userQuery = pg_query_params($conn, $userSql, array($name, $password, $role));
  if ($userQuery && $result = pg_fetch_assoc($userQuery)) {
    return $result;
  }
}

$id = json_encode(addUser($conn, $_POST['name'], $_POST['password'], $_POST['role']));
$file = fopen('jsonstuff2.txt', 'a');
fwrite($file, $id);
echo $id;
return $id;
