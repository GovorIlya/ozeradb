import { BaseEntity } from './../../shared';

export class Unit implements BaseEntity {
    constructor(
        public id?: number,
        public unitName?: string,
        public description?: BaseEntity,
        public images?: BaseEntity[],
        public researchMethod?: BaseEntity,
        public ratingMethod?: BaseEntity,
        public typesProblems?: BaseEntity,
    ) {
    }
}
