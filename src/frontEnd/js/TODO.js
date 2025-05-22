class Todohandler {
    #form;
    #titleInput;
    #descriptionInput;
    #deadlineInput;
    #categorySelect;
    #submitButton;
    #toggleStateSearchButton;
    #utilitiesButtons
    #searchByDateForm;
    #searchByDateButton;
    #searchByDateSubmitButton;
    #searchByDatePreInput;
    #searchByDatePostInput;
    #searchByDateSelect;
    #viewingCompleted;
    #cardList;
    #categories = [];
    #today;

    constructor() {
        this.#form = document.querySelector("#form");
        this.#titleInput = document.querySelector("#inputTitle");
        this.#descriptionInput = document.querySelector("#inputDescription");
        this.#deadlineInput = document.querySelector("#inputDeadline");
        this.#categorySelect = document.querySelector("#selectCategory");
        this.#submitButton = document.querySelector("#ButtonSubmit");

        this.#cardList = document.querySelector("#CardList");

        this.#utilitiesButtons = document.querySelector("#buttons");

        this.#toggleStateSearchButton = document.querySelector("#ToggleFinished");

        this.#searchByDateButton = document.querySelector("#ToggleSearchByDate");

        this.#searchByDateForm = document.querySelector("#searchByDateForm");
        this.#searchByDatePreInput = document.querySelector("#inputDatePre");
        this.#searchByDatePostInput = document.querySelector("#inputDatePost");
        this.#searchByDateSelect = document.querySelector("#searchDateButton");
        this.#searchByDateSubmitButton = document.querySelector("#searchByDateForm button");

        this.#viewingCompleted = false;
        this.#toggleStateSearchButton.textContent = "Show " + (this.#viewingCompleted ? "Unfinished" : "finished");
        this.#today = new Date().toISOString().split('T')[0];

        this.populateCategoriesSelect();
        this.populateCardList(this.searchForstate(this.#viewingCompleted));

        this.#submitButton.addEventListener("click", async () => {
            if (this.#titleInput.value !== "" && this.#descriptionInput.value !== "" && this.#deadlineInput.value !== "") {
                await this.postCard(
                    this.#titleInput.value,
                    this.#descriptionInput.value,
                    this.#deadlineInput.value,
                    Number(this.#categorySelect.value)
                );
                await this.populateCardList(this.searchForstate(this.#viewingCompleted));
                this.#form.reset();
            } else {
                let errorMess = "Inserisci";
                if (!this.#titleInput.value) errorMess += " titolo";
                if (!this.#descriptionInput.value) errorMess += " descrizione";
                if (!this.#deadlineInput.value) errorMess += " deadline";
                console.log(errorMess);
            }
        });

        this.#toggleStateSearchButton.addEventListener("click", () => {
            this.#viewingCompleted = !this.#viewingCompleted;
            this.populateCardList(this.searchForstate(this.#viewingCompleted));
            this.#toggleStateSearchButton.textContent = "Show " + (this.#viewingCompleted ? "Unfinished" : "finished");
        });

        this.#searchByDateButton.addEventListener("click", () => {
            this.#searchByDateForm.classList.toggle("hidden");
            this.#utilitiesButtons.classList.toggle("collapsed2ndRow");
            if(this.#searchByDateForm.classList.contains("hidden")){
                this.populateCardList(this.searchForstate(this.#viewingCompleted));
            }
        });

        this.#searchByDateSubmitButton.addEventListener("click", async () => {
            const preDate = this.#searchByDatePreInput.value;
            const postDate = this.#searchByDatePostInput.value;
            const selectValue = this.#searchByDateSelect.value;

            if (preDate !== "" && postDate !== "") {
                this.populateCardList(await this.searchByDate(preDate, postDate, selectValue));
            } else {
                let errorMess = "Inserisci";
                if (!preDate) errorMess += " data iniziale";
                if (!postDate) errorMess += " data finale";
                console.log(errorMess);
            }
        });
    }

    async getCategories() {
        const categories = await fetch("http://localhost:8080/api/category");
        if (!categories.ok) {
            alert("Something went wrong while gathering the categories");
            return;
        }
        this.#categories = await categories.json();
    }

    async populateCategoriesSelect() {
        await this.getCategories();
        this.#categories.forEach(category => {
            const option = document.createElement("option");
            option.textContent = category.title;
            option.value = category.categoryId;
            this.#categorySelect.appendChild(option);
        });
    }

    async postCard(title, description, deadline, category) {
        try {
            await fetch("http://localhost:8080/api/card", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    title: title,
                    description: description,
                    creationDate: this.#today,
                    deadline: deadline,
                    completed: false,
                    categoryId: category
                })
            });
        } catch (error) {
            console.log("Something went wrong during the creation, sorry");
        }
    }

    async searchForstate(state) {
        const response = await fetch("http://localhost:8080/api/card?state=" + state);
        if (response.status !== 200) {
            console.log("Couldn't find cards");
            return [];
        }
        return await response.json();
    }

    async populateCardList(inputList) {
        const cardListData = await inputList;
        this.#cardList.innerHTML = "";
        if (cardListData.length === 0) {
            console.log("Non ho cards da mostrare");
        } else {
            cardListData.forEach((card) => {
                console.log(card);
                
                const cardContainer = document.createElement("div");
                const cardTitle = document.createElement("h2");
                const cardDescription = document.createElement("p");
                const cardCategory = document.createElement("u");
                const markAsCompleted = document.createElement("button");
                const cardDeadline = document.createElement("p");
                const cardCreationDate = document.createElement("p");
                const datesContainer = document.createElement("div");
                const buttonContainer = document.createElement("div")
                const deleteCardButton = document.createElement("button");
                buttonContainer.id ="buttonContainer";

                cardTitle.textContent = card.title;
                cardDescription.textContent = card.description;

                this.#categories.forEach((category) => {
                    if (category.categoryId === card.categoryId) {
                        cardCategory.textContent = category.title;
                    }
                });

                cardDeadline.textContent = "deadline: " + card.deadline;

                markAsCompleted.textContent = "Mark as completed";

                if (card.completed === true) {
                    cardContainer.classList.add("finished");
                    markAsCompleted.textContent="Mark as Uncompleated";
                    
                    deleteCardButton.textContent="Delete"
                    buttonContainer.appendChild(deleteCardButton);
                }

                buttonContainer.appendChild(markAsCompleted);
                cardCreationDate.textContent = "creation date: " + card.creationDate;
                datesContainer.append(cardDeadline, cardCreationDate);


                

                cardContainer.append(cardTitle, cardDescription, cardCategory, datesContainer,buttonContainer);
                this.#cardList.appendChild(cardContainer);

                markAsCompleted.addEventListener("click", async () => {
                    await this.markCardAsCompleted(card);
                    await this.populateCardList(this.searchForstate(this.#viewingCompleted));
                });

                deleteCardButton.addEventListener("click",async()=>{
                    await this.deleteCard(card.cardId);
                    this.populateCardList((this.searchForstate(this.#viewingCompleted)));
                })

            });
        }
    }

    async markCardAsCompleted(card) {
        try {
            await fetch("http://localhost:8080/api/card", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    ...card,
                    completed: !this.#viewingCompleted
                })
            });
        } catch (error) {
            console.log("Something went wrong during the creation, sorry");
        }
    }

    async searchByDate(pre, post, type) {
        try {
            const response = await fetch(`http://localhost:8080/api/card?pre=${pre}&post=${post}&dtc=${type}`);

            if (response.status !== 200) {
                console.log("Found no cards with this criteria");
                return [];
            } else {
                const jsonResp = await response.json();
                return jsonResp;
            }
        } catch (error) {
            console.error("Error fetching cards by date:", error);
            return [];
        }
    }

    async deleteCard(id){
        try {
            console.log(id);
            
            const response = await fetch(`http://localhost:8080/api/card/`+id,
                {method: 'DELETE'}
                );
            if (response.status !== 200) {
                console.log("Error during card deleting");
            } 
        } catch (error) {
            console.error("Error fetching cards by date:", error);
        }
    }
}

const tdh = new Todohandler();