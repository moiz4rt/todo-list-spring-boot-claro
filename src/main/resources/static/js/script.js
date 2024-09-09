function deleteItem(button) {
    let id = button.closest('li').getAttribute("data-id")
    if (confirm("Tem certeza que deseja deletar este item?")) {
        fetch(`/items/${id}`, {
            method: 'DELETE'
        })
        .then(response => {
            if (response.ok) {
                // Remove o item da lista visualmente
                document.querySelector(`li[data-id="${id}"]`).remove();
            } else {
                alert("Erro ao deletar o item.");
            }
        })
        .catch(error => console.error("Erro:", error));
    }
}

function updateItem(button)
{
    const itemId = button.closest('li').getAttribute('data-id');
    const li = button.closest('li');
    const titulo = li.querySelector('.titulo').innerText;
    const descricao = li.querySelector('.descricao').innerText;
    li.innerHTML = `
    <input type="text" id="newTitulo" value="${titulo}" />
    <input type="text" id="newDescricao" value="${descricao}" />
    <button onclick="saveUpdate(${itemId}, this)">Salvar</button>
    `;
}

function saveUpdate(itemId, button)
{
    const li = button.closest('li');
    const newTitulo = li.querySelector('#newTitulo').value;
    const newDescricao = li.querySelector('#newDescricao').value;
    fetch(`/items/${itemId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ titulo: newTitulo, descricao: newDescricao  })
    })
        .then(response => {
            if(response.ok)
            {
                li.innerHTML = `
                <p class="titulo">${newTitulo}</p>
                <p class="descricao">${newDescricao}</p>
                <button onclick="deleteItem(this)">DELETE</button>
                <button onclick="updateItem(this)">UPDATE</button>
                `;
            }
            else
            {
                console.error('Erro ao atualizar item');
            }                
        })
}
