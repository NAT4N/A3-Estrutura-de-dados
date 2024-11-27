
let cart = JSON.parse(localStorage.getItem('cart')) || [];

function addToCart(productName, productPrice) {
  const existingItemIndex = cart.findIndex(item => item.name === productName);

  if (existingItemIndex !== -1) {
      cart[existingItemIndex].quantity += 1;
  } else {
      cart.push({ name: productName, price: productPrice, quantity: 1 });
  }

  localStorage.setItem('cart', JSON.stringify(cart));
  alert(productName + ' foi adicionado ao seu carrinho!');
  window.location.href = 'carrinho.html';
}

function displayCartItems() {
  const cartItems = JSON.parse(localStorage.getItem('cart')) || [];
  const cartList = document.getElementById('cartItems');
  const totalPriceElement = document.getElementById('totalPrice');
  cartList.innerHTML = '';
  let total = 0;

  if (cartItems.length === 0) {
      cartList.innerHTML = "<li>Seu carrinho está vazio.</li>";
      totalPriceElement.textContent = '0.00';
      localStorage.setItem('totalPrice', '0.00'); // Armazena o total no localStorage
  } else {
      cartItems.forEach((item, index) => {
          const li = document.createElement('li');
          li.className = 'cart-item';
          li.textContent = `${item.name} - R$${item.price.toFixed(2)} (Quantidade: ${item.quantity})`;
          total += item.price * item.quantity; // Multiplica o preço pela quantidade

          const removeButton = document.createElement('button');
          removeButton.textContent = 'Remover';
          removeButton.className = 'remove-button';
          removeButton.onclick = () => removeFromCart(index);

          li.appendChild(removeButton);
          cartList.appendChild(li);
      });
      totalPriceElement.textContent = total.toFixed(2);
      localStorage.setItem('totalPrice', total.toFixed(2));
  }
}

function removeFromCart(index) {
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  
  if (cart[index].quantity > 1) {
      cart[index].quantity -= 1;
  } else {
      cart.splice(index, 1);
  }

  localStorage.setItem('cart', JSON.stringify(cart));
  displayCartItems();
}


async function finalizarCompra() {
    const totalPrice = localStorage.getItem('totalPrice');
    const idCliente = localStorage.getItem('clientId');
    const paymentMethod = document.getElementById('payment-method').value;

    const dadosPagamento = {
        valor: totalPrice,
        idCliente: idCliente,
        tipo: paymentMethod,
        produtos: JSON.parse(localStorage.getItem('cart'))
    };

    const jsonDadosPagamento = JSON.stringify(dadosPagamento);
    console.log(jsonDadosPagamento);
    
    const url = "v1/transacao/nova";

    try {
        const resultado = await fetch(url, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: jsonDadosPagamento
        });

        if (resultado.status == 200) {
          alert("Pagamento finalizado!");
        } else {
            console.log("Produto não encontrado");
        }
    } catch (error) {
        console.error("Erro ao buscar dados:", error);
    }
}


document.addEventListener('DOMContentLoaded', () => {
    const path = window.location.pathname;

    if (path.includes('index.html')) {
    } else if (path.includes('carrinho.html')) {
        displayCartItems();
    } else if (path.includes('pilha.html')) {
        const btn = document.getElementById("btn_enviar");
        const txtInput = document.getElementById("txt_input");
        const txtOutput = document.getElementById("txt_output");

    } else if (path.includes('pagamento.html')) {
        const totalPriceDisplay = document.getElementById('totalPriceDisplay');
        const totalPrice = localStorage.getItem('totalPrice') || '0.00';
        totalPriceDisplay.textContent = `R$${totalPrice}`;
    }

    checkLoginStatus();
});

function checkLoginStatus() {
    const clientId = localStorage.getItem('clientId');
    if (clientId) {
        document.getElementById('login-button').style.display = 'none';
        document.getElementById('logout-button').style.display = 'block';
    } else {
        document.getElementById('login-button').style.display = 'block';
        document.getElementById('logout-button').style.display = 'none';
    }
}

function logout() {
    localStorage.removeItem('clientId');
    checkLoginStatus();
}

function login() {
    const email = document.querySelector('input[placeholder="Email"]').value;
    const senha = document.querySelector('input[placeholder="Senha"]').value;


    if (!email || !senha) {
        alert('Por favor, preencha todos os campos de login.');
        return;
    }


    fetch('v1/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            email: email,
            password: senha
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.id) {
                alert('Login bem-sucedido!');
                localStorage.setItem('clientId', data.id);
                window.location.href = 'index.html';
            } else {
                alert('Erro no login: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Erro ao fazer login:', error);
            alert('Erro no servidor.');
        });
    checkLoginStatus();
}

function register() {
    // Capturando os dados de registro
    const nome = document.querySelector('input[placeholder="Nome"]').value;
    const email = document.querySelector('input[placeholder="Email Cadastro"]').value;
    const senha = document.querySelector('input[placeholder="Senha Cadastro"]').value;

    if (!nome || !email || !senha) {
        alert('Por favor, preencha todos os campos de registro.');
        return;
    }

    fetch('v1/cadastro', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: nome,
            email: email,
            password: senha
        })
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                alert('Cadastro realizado com sucesso!');
                document.querySelector('input[placeholder="Nome"]').value = '';
                document.querySelector('input[placeholder="Email Cadastro"]').value = '';
                document.querySelector('input[placeholder="Senha Cadastro"]').value = '';
            } else {
                // Caso haja erro no cadastro
                alert('Erro no cadastro: ' + data.message);
            }
        })
        .catch(error => {
            console.error('Erro ao fazer cadastro:', error);
            alert('Erro no servidor.');
        });
}

function toggleDetails(orderId) {
    var details = document.getElementById("order-details-" + orderId);
    if (details.style.display === "block") {
        details.style.display = "none";
    } else {
        details.style.display = "block";
    }
}

async function fetchData() {
    try {
        const response = await fetch("v1/transacao/consultar?clientId=" + localStorage.getItem("clientId"), {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
            cache: "no-store"
        });

        if (!response.ok) {
            console.error("Erro na requisição:", response.status, response.statusText);
            return;
        }

        const data = await response.json();
        renderTable(data);
    } catch (error) {
        console.error('Erro ao buscar dados:', error);
    }
}

function renderTable(data) {
    const tableBody = document.querySelector("#dataTable tbody");
    tableBody.innerHTML = '';

    if (!Array.isArray(data) || data.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="5">Nenhum dado encontrado.</td></tr>';
        return;
    }

    data.forEach(item => {
        const row = document.createElement("tr");

        const idCell = document.createElement("td");
        idCell.textContent = item.idTransacao || 'N/A';
        row.appendChild(idCell);

        const dateTimeCell = document.createElement("td");
        dateTimeCell.textContent = item.dataTransacao || 'N/A';
        row.appendChild(dateTimeCell);

        const valueCell = document.createElement("td");
        valueCell.textContent = "R$ " + (item.valor ? item.valor : '0.00');
        row.appendChild(valueCell);

        const tipoCell = document.createElement("td");
        tipoCell.textContent = item.tipo || 'N/A';
        row.appendChild(tipoCell);

        const actionCell = document.createElement("td");
        const expandButton = document.createElement("button");
        expandButton.textContent = "Ver Detalhes";
        expandButton.classList.add("expand-button");
        expandButton.addEventListener("click", () => toggleInfo(row, expandButton, item.produtos));
        actionCell.appendChild(expandButton);
        row.appendChild(actionCell);

        tableBody.appendChild(row);

        // Linha adicional para os detalhes dos produtos
        const extraRow = document.createElement("tr");
        extraRow.classList.add("extra-info");

        const extraCell = document.createElement("td");
        extraCell.colSpan = 5;

        const subTable = document.createElement("table");
        subTable.classList.add("sub-table");

        const subTableHead = document.createElement("thead");
        const subTableHeadRow = document.createElement("tr");
        ["Produto", "Quantidade", "Preço"].forEach(text => {
            const th = document.createElement("th");
            th.textContent = text;
            subTableHeadRow.appendChild(th);
        });
        subTableHead.appendChild(subTableHeadRow);
        subTable.appendChild(subTableHead);

        const subTableBody = document.createElement("tbody");
        if (Array.isArray(item.produtos)) {
            item.produtos.forEach(produto => {
                const subRow = document.createElement("tr");

                const nameCell = document.createElement("td");
                nameCell.textContent = produto.name || 'N/A';
                subRow.appendChild(nameCell);

                const quantityCell = document.createElement("td");
                quantityCell.textContent = produto.quantity || '0';
                subRow.appendChild(quantityCell);

                const priceCell = document.createElement("td");
                priceCell.textContent = "R$ " + (produto.price ? produto.price.toFixed(2) : '0.00');
                subRow.appendChild(priceCell);

                subTableBody.appendChild(subRow);
            });
        }
        subTable.appendChild(subTableBody);
        extraCell.appendChild(subTable);
        extraRow.appendChild(extraCell);

        tableBody.appendChild(extraRow);
    });
}

function toggleInfo(row, expandButton) {
    const extraRow = row.nextSibling;
    const isExpanded = extraRow.style.display === "table-row";
    expandButton.classList.toggle("expanded", !isExpanded);
    extraRow.style.display = isExpanded ? "none" : "table-row";
}