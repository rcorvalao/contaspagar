import { Contato } from "./contato";

export class Agenda {
    public id: number;
    public contatos: Contato[];

    constructor() {
        this.contatos = [];
    }
}
