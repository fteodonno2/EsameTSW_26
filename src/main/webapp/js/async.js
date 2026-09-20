document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("feedbackForm");
    const messageDiv = document.getElementById("feedbackMessage");

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const feedbackData = {
            name: document.getElementById("name").value.trim(),
            email: document.getElementById("email").value.trim(),
            rating: parseInt(document.getElementById("rating").value),
            comments: document.getElementById("comments").value.trim()
        };

        if (!feedbackData.name || !feedbackData.email || !feedbackData.rating || !feedbackData.comments) {
            messageDiv.textContent = "Per favore compila tutti i campi.";
            messageDiv.classList.add("error");
            return;
        }

        try {
            const response = await fetch("feedback", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(feedbackData)
            });

            const result = await response.json();

            if (result.status === "success") {
                messageDiv.textContent = result.message || "Grazie per il tuo feedback!";
                messageDiv.classList.add("success");
                form.reset();
            } else {
                messageDiv.textContent = result.message || "Errore durante l'invio del feedback.";
                messageDiv.classList.add("error");
            }
        } catch (error) {
            messageDiv.textContent = `Errore di connessione: ${error.message}`;
            messageDiv.classList.add("error");
        }
    });
});
