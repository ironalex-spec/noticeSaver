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
        responseJSON = await getPostRequest('/auth', dataToSendForm,'POST',);

        if (typeof responseJSON !== 'undefined' && responseJSON !== null) {
            //Save response to the coockey
            document.cookie = "token=" + responseJSON.token;

            logInUser();
        }
        else {
            document.cookie = "token=''";
        }
    };
 };

async function logInUser() {
    getPostRequest('/auth/user','' ,'GET', { 'Content-Type': 'application/json; charset=UTF-8',
                                           'Authorization': 'Bearer ' + getCookie('token')
                                            });
};

async function getPostRequest(url = '', data = {}, methodGetPost = 'GET',
                header = {'Content-Type': 'application/json; charset=UTF-8'}) {


    let responseServer;

    if (data == '')  {
        responseServer = await fetch(url, {
                      method: methodGetPost, // *GET, POST, PUT, DELETE, etc.
                      mode: 'same-origin', // no-cors, *cors, same-origin
                      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
                      credentials: 'same-origin', // include, *same-origin, omit
                      headers: header,
                      redirect: 'follow', // manual, *follow, error
                      referrerPolicy: 'no-referrer'
                    });
    } else {
        // Default options are marked with *
            responseServer = await fetch(url, {
              method: methodGetPost, // *GET, POST, PUT, DELETE, etc.
              mode: 'same-origin', // no-cors, *cors, same-origin
              cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
              credentials: 'same-origin', // include, *same-origin, omit
              headers: header,
              redirect: 'follow', // manual, *follow, error
              referrerPolicy: 'no-referrer', // no-referrer, *client
              body: JSON.stringify(data) // body data type must match "Content-Type" header
            });
    }

     let jsonResponse = '';

     if (responseServer.ok) { // если HTTP-статус в диапазоне 200-299
        if (responseServer.redirected) {
            window.location = responseServer.url;
        }
        else {
            // получаем тело ответа (см. про этот метод ниже)
           jsonResponse = await responseServer.json();
        }

     } else {
       console.log("Ошибка HTTP: " + responseServer.status);
     }

       return jsonResponse;
}

 function getCookie(cname) {
   let name = cname + "=";
   let decodedCookie = decodeURIComponent(document.cookie);
   let ca = decodedCookie.split(';');
   for(let i = 0; i <ca.length; i++) {
     let c = ca[i];
     while (c.charAt(0) == ' ') {
       c = c.substring(1);
     }
     if (c.indexOf(name) == 0) {
       return c.substring(name.length, c.length);
     }
   }
   return "";
 }
