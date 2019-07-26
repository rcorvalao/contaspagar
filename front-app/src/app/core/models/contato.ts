import { Fone } from './fone';

export class Contato {
    public id: number;
    nome: number;
    apelido: string;
    email: string;
    fones: Fone[];
    constructor() {
        this.fones = [];
    }
}

