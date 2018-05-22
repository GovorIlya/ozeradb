import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { OzeradbSharedModule } from '../../shared';
import {
    ProblemtypesService,
    ProblemtypesComponent,
    ProblemtypesRoute,
} from './';
import {UnitService} from '../../entities/unit';
import {TypesProblemsService} from '../../entities/types-problems';
import {PdfViewerModule} from 'ng2-pdf-viewer';

const PAGE_SET_STATES = [
    ...ProblemtypesRoute,
];

@NgModule({
    imports: [
        PdfViewerModule,
        OzeradbSharedModule,
        RouterModule.forRoot(PAGE_SET_STATES, { useHash: true })
    ],
    declarations: [
    ProblemtypesComponent,
],
    entryComponents: [
    ProblemtypesComponent,
],
    providers: [
    ProblemtypesService,
        UnitService,
        TypesProblemsService
],
schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class OzeradbProblemtypesModule {}
