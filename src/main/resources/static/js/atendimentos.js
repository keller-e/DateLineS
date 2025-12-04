function salvarAtendimento() {
    const payload = {
        data: $("#data").val(),
        hora: $("#hora").val(),
        procedimento: $("#procedimento").val(),
        formaPagamento: $("#formaPagamento").val()
                // não enviar cliente completo; o endpoint POST /api/atendimentos/{clienteId} espera clienteId no path
    };
    const clienteId = $("#clienteIdSelect").val();

    $.ajax({
        url: "/api/atendimentos/" + clienteId,
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(payload),
        success: function () {
            window.location.href = "/atendimentos";
        }
    });
}

function removerAtendimento(id) {
    if (!confirm("Deseja remover o atendimento?"))
        return;

    fetch("/api/atendimentos/" + id, {method: "DELETE"})
            .then(() => location.reload());
}