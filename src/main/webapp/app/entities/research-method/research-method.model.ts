import { BaseEntity } from './../../shared';

export class ResearchMethod implements BaseEntity {
    constructor(
        public id?: number,
        public fileContentType?: string,
        public file?: any,
        public units?: BaseEntity[],
    ) {
    }
}
