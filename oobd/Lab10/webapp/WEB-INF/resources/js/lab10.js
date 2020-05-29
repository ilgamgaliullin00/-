//console.log(a);
/*
 AUTOCOMLETE BEGIN
*/
/*
    Элемент управления Autocomplete:
    <div class="autocomplete" style="width:600px;">
        <input id="org" type="text" name="org" placeholder="Организация">
    </div>
	<script>
		//autocomplete(input_element_id, url_to_dict_values)
		autocomplete("org","/app_name/dict/organization");
	</script>

    POST запрос к url_to_dict_values должен возвращать JSON массив в формате
    [{"id":"id_values","name":"name_value"},...]

    Код выбранного элемента справочника фиксируется в скрытом поле с html_id = input_element_id + '_id'

*/
var TIME_WAIT = 400;
var t_send = new Date();

function autocomplete(inp_name, resource_url) {
    var inp = document.getElementById(inp_name);
    /*the autocomplete function takes two arguments,
    the text field element and an array of possible autocompleted values:*/
    var currentFocus;
    var h;

    h = document.createElement("input");
    h.setAttribute("type", "hidden");
    h.setAttribute("name", inp.getAttribute("name")+"_id");
    h.setAttribute("id", inp.getAttribute("id")+"_id");
    inp.parentNode.appendChild(h);


    inp.parentNode.addEventListener("focusout", function(){
        console.log("focusout");
        e=document.getElementById(inp.id+"_id").value;
        if (!e || e=='') inp.value='';
        //closeAllLists();
    });


    /*execute a function when someone writes in the text field:*/
    inp.addEventListener("input", function(e) {
        var a, b, i, val = this.value;
        var element = this;
        document.getElementById(inp.id+"_id").value = '';
        /*close any already open lists of autocompleted values*/
        closeAllLists();
        if (!val) { return false;}
        currentFocus = -1;

        // get list
        if (t_send != 0) {
            t_send=0;
            setTimeout(function(){
                t_send=new Date();
                getarr(inp, element, resource_url);
            }, TIME_WAIT);
        }
    });

    /*execute a function presses a key on the keyboard:*/
    inp.addEventListener("keydown", function(e) {
        var x = document.getElementById(this.id + "autocomplete-list");
        if (x) x = x.getElementsByTagName("div");
        if (e.keyCode == 40) {
            /*If the arrow DOWN key is pressed,
            increase the currentFocus variable:*/
            currentFocus++;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 38) { //up
            /*If the arrow UP key is pressed,
            decrease the currentFocus variable:*/
            currentFocus--;
            /*and and make the current item more visible:*/
            addActive(x);
        } else if (e.keyCode == 13) {
            /*If the ENTER key is pressed, prevent the form from being submitted,*/
            e.preventDefault();
            if (currentFocus > -1) {
              /*and simulate a click on the "active" item:*/
              if (x) x[currentFocus].click();
            }
        } else if (e.keyCode == 27) {
            closeAllLists();
        }
    });

    function findinarrbyid(id,arr) {
        var res;
        for (i = 0; i < arr.length; i++) {
          if (arr[i]["id"]==id) {res=arr[i]["name"];break;}
        }
        return res;
    }

    function addActive(x) {
        /*a function to classify an item as "active":*/
        if (!x) return false;
        /*start by removing the "active" class on all items:*/
        removeActive(x);
        if (currentFocus >= x.length) currentFocus = 0;
        if (currentFocus < 0) currentFocus = (x.length - 1);
        /*add class "autocomplete-active":*/
        x[currentFocus].classList.add("autocomplete-active");
    }

    function removeActive(x) {
    /*a function to remove the "active" class from all autocomplete items:*/
        for (var i = 0; i < x.length; i++) {
          x[i].classList.remove("autocomplete-active");
        }
    }

    function closeAllLists(elmnt) {
    /*close all autocomplete lists in the document,
    except the one passed as an argument:*/
        var x = document.getElementsByClassName("autocomplete-items");
        for (var i = 0; i < x.length; i++) {
          if (elmnt != x[i] && elmnt != inp) {
            x[i].parentNode.removeChild(x[i]);
          }
        }
    }

    function getarr(inp, element, resource_url) {
        var a, b, i, name = inp.value, val=inp.value;
        var t_s=new Date();

      var xhr = new XMLHttpRequest();
      xhr.open("POST", resource_url, true);
      xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded; charset=utf-8");
      var params = 'name=' + encodeURIComponent(name);
      xhr.onreadystatechange = function () {
          if ((xhr.status == 200) && (xhr.readyState == 4)) {
                var dict_values = JSON.parse(xhr.response);

                /*create a DIV element that will contain the items (values):*/
                a = document.createElement("DIV");
                a.setAttribute("id", inp.id + "autocomplete-list");
                a.setAttribute("class", "autocomplete-items");
                /*append the DIV element as a child of the autocomplete container:*/
                element.parentNode.appendChild(a);
                /*for each item in the array...*/
                for (i = 0; i < dict_values.length; i++) {
                /*check if the item starts with the same letters as the text field value:*/
                //if (dict_values[i]['name'].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                    /*create a DIV element for each matching element:*/
                    b = document.createElement("DIV");
                    var p=0;
                    p = dict_values[i]['name'].toUpperCase().indexOf(val.toUpperCase());
                    b.innerHTML = '';
                    if (p>0) b.innerHTML = dict_values[i]['name'].substr(0, p); else p=0;
                    /*make the matching letters bold:*/
                    b.innerHTML += "<strong>" + dict_values[i]['name'].substr(p, val.length) + "</strong>";
                    b.innerHTML += dict_values[i]['name'].substr(p + val.length);
                    /*insert a input field that will hold the current array item's value:*/
                    b.innerHTML += "<input type='hidden' value='" + dict_values[i]["id"] + "'>";
                    /*execute a function when someone clicks on the item value (DIV element):*/
                    b.addEventListener("click", function(e) {
                            console.log("click");

                        /*insert the value for the autocomplete text field:*/
                        inp.value = findinarrbyid(this.getElementsByTagName("input")[0].value, dict_values);
                        document.getElementById(inp.id+"_id").value = this.getElementsByTagName("input")[0].value;
                        /*close the list of autocompleted values,
                        (or any other open lists of autocompleted values:*/
                        closeAllLists();
                    });
                    a.appendChild(b);
                //}
             }

          } else {
          }
      }
      xhr.send(params);
    }

    /*execute a function when someone clicks in the document:*/
    document.addEventListener("click", function (e) {
      closeAllLists(e.target);
    });
}
/*
 AUTOCOMLETE END
*/

function formatDate(d) {
    return d.getDate()+'.'+d.getMonth() +'.'+ d.getFullYear();
}