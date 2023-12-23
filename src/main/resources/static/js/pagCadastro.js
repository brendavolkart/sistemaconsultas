$(document).ready(function () {
    if ($(".modal-body").length > 0) {
        $('#modal-retorno-form').modal();
    }
    
    $('#formNomeTelefoneCPF').submit(function (event) {
        var cpfInput = $('#cpfInput').val();
        var telefoneInput = $('#telefoneInput').val();

        if (!validarCPF(cpfInput)) {
            alert('CPF inválido! Por favor, corrija.');
            event.preventDefault();
        } else if (!validarTelefone(telefoneInput)) {
            alert('Número de telefone inválido! Insira no formato (XX) XXXX-XXXX ou (XX) XXXXX-XXXX.');
            event.preventDefault();
        }
    });

    $('#formNomeCPFCRM').submit(function (event) {
        var cpfInput2 = $('#cpfInput2').val();
        var crmInput2 = $('#crmInput').val();

        if (!validarCPF(cpfInput2)) {
            alert('CPF inválido! Por favor, corrija.');
            event.preventDefault();
        }
    });

    function validarCPF(cpf) {
        cpf = cpf.replace(/[^\d]+/g, ''); // Remove caracteres não numéricos
        if (cpf.length !== 11 || /^(.)\1+$/.test(cpf)) {
            return false; // CPF deve ter 11 dígitos e não pode ter todos os dígitos iguais
        }

        let soma = 0;
        for (let i = 0; i < 9; i++) {
            soma += parseInt(cpf.charAt(i)) * (10 - i);
        }

        let resto = 11 - (soma % 11);
        resto = (resto === 10 || resto === 11) ? 0 : resto;

        if (resto !== parseInt(cpf.charAt(9))) {
            return false;
        }

        soma = 0;
        for (let i = 0; i < 10; i++) {
            soma += parseInt(cpf.charAt(i)) * (11 - i);
        }

        resto = 11 - (soma % 11);
        resto = (resto === 10 || resto === 11) ? 0 : resto;

        return resto === parseInt(cpf.charAt(10));
    }

    function validarTelefone(telefone) {
        var regex = /^\(\d{2}\) \d{4,5}-\d{4}$/;
        return regex.test(telefone);
    }
});