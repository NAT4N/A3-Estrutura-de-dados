const btn = document.getElementById("btn_enviar");
const txtInput = document.getElementById("txt_input");
const txtOutput = document.getElementById("txt_output");

btn.addEventListener("click", getData);

async function getData() {
    let url = "http://localhost:8080/v1/transacao/consolidar";

    let resultado = await fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            "valor": 57463,
            "idCliente": "ASJHDBA-897IKJHBK-AHSBD787",
            "tipo": "PIX",
            "produtos": [
                {
                    "name": "Prod1",
                    "description": "asdasd",
                    "price": 10.22,
                    "quantity": 5
                },
                {
                    "name": "Prod2",
                    "description": "asdasd",
                    "price": 10.22,
                    "quantity": 5
                },
                {
                    "name": "Prod3",
                    "description": "asdasd",
                    "price": 10.22,
                    "quantity": 5
                },
                {
                    "name": "Prod15",
                    "description": "asdasd",
                    "price": 10.22,
                    "quantity": 5
                },
                {
                    "name": "Prod1",
                    "description": "asdasd",
                    "price": 10.22,
                    "quantity": 5
                }
            ]
        })
    });

    console.log(resultado);

    if (resultado.status == 200) {
        let dados = await resultado.json();
        console.log(dados);
        txtOutput.value = JSON.stringify(dados);
    } else {
        console.log("Produto não encontrado");
    }
}