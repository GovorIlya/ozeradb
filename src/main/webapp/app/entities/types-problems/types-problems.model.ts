import { BaseEntity } from './../../shared';

export class TypesProblems implements BaseEntity {
    constructor(
        public id?: number,
        public problemDocumentContentType?: string,
        public problemDocument?: any,
        public units?: BaseEntity[],
    ) {
    }
}
