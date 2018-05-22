import { BaseEntity } from './../../shared';

export class RatingMethod implements BaseEntity {
    constructor(
        public id?: number,
        public ratingDocumentContentType?: string,
        public ratingDocument?: any,
        public units?: BaseEntity[],
    ) {
    }
}
