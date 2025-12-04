function removerCliente(id) {
    if (!confirm("Deseja remover o cliente?"))
        return;

    fetch("/api/clientes/" + id, {method: "DELETE"})
            .then(() => location.reload());
}