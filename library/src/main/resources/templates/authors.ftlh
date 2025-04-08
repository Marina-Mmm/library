<html lang="en">
<head>
  <link rel="stylesheet" href="/authors.css", type="text/css">
</head>
<body>
<div class="container">
    <h2>AUTHORS</h2>
    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">ID</div>
            <div class="col col-2">SURNAME</div>
            <div class="col col-3">NAME</div>

        </li>
        <#list authors as author>
            <li class="table-row">
                <div class="col col-1">${author.id}</div>
                <div class="col col-2">${author.surname}</div>
                <div class="col col-3">${author.name}</div>
            </li>
        </#list>
    </ul>
</div>
</body>
</html>