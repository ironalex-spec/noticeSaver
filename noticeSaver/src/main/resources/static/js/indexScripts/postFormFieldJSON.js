async function submitButton() {
    if (validateIn()){
        var inputs = document.getElementById("auth-form").elements;

        let dataToSendForm = {};

        for (i = 0; i < inputs.length; i++) {
          if (inputs[i].nodeName === "INPUT" && inputs[i].type !== "checkbox") {
            // Update text input
            dataToSendForm[inputs[i].name.toString()] = inputs[i].value;
          }
        }

        //for testing send JSON object
        const dataToSendConst = {"login": "test", "password": "test"};

        //get request from server
        responseJSON = await postData('/auth', dataToSendForm);

        if (typeof responseJSON !== 'undefined' && responseJSON !== null) {
            console.log("response token = " + responseJSON.token);
        }
        else {
            console.log("Ошибка ответа с сервера: Data=" + responseJSON);
        }
    };
 };

 async function postData(url = '', data = {}) {
   // Default options are marked with *
   let responseServer = await fetch(url, {
     method: 'POST', // *GET, POST, PUT, DELETE, etc.
     mode: 'same-origin', // no-cors, *cors, same-origin
     cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
     credentials: 'same-origin', // include, *same-origin, omit
     headers: {
       'Content-Type': 'application/json; charset=UTF-8'
       // 'Content-Type': 'application/x-www-form-urlencoded',
     },
     redirect: 'follow', // manual, *follow, error
     referrerPolicy: 'no-referrer', // no-referrer, *client
     body: JSON.stringify(data) // body data type must match "Content-Type" header

   }); /*.then(response => response.json())
      .then(json => {
            console.log(json);
            responseServer = json;
        })
      .catch(err => {
          if (err === "server") return
          console.log(err)
      });*/

    let jsonResponse;
    if (responseServer.ok) { // если HTTP-статус в диапазоне 200-299
      // получаем тело ответа (см. про этот метод ниже)
      jsonResponse = await responseServer.json();
    } else {
      console.log("Ошибка HTTP: " + responseServer.status);
    }

      return jsonResponse;
 }

