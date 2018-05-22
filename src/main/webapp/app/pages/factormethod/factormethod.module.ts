import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OzeradbSharedModule } from '../../shared';
import {
    FactormethodService,
    FactormethodComponent,
    FactormethodRoute,
} from './';
import {PdfViewerModule} from 'ng2-pdf-viewer';
import {ResearchMethodService} from '../../entities/research-method';
import {UnitService} from '../../entities/unit';
import {RatingMethodService} from '../../entities/rating-method';

const PAGE_SET_STATES = [
    ...FactormethodRoute,
];

@NgModule({
    imports: [
        PdfViewerModule,
        OzeradbSharedModule,
        RouterModule.forRoot(PAGE_SET_STATES, { useHash: true })
    ],
    declarations: [
    FactormethodComponent,
],
    entryComponents: [
    FactormethodComponent,
],
    providers: [
    FactormethodService,
        ResearchMethodService,
        UnitService,
        RatingMethodService
],
schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class OzeradbFactormethodModule {}
