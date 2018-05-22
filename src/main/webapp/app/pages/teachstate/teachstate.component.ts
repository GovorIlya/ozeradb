import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
 import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';
 import { Observable } from 'rxjs/Rx';

import { Teachstate } from './teachstate.model';
import { TeachstateService } from './teachstate.service';
import { Principal } from '../../shared';

import {ImageService} from '../../entities/image';
import {Image} from '../../entities/image';
import {Unit} from '../../entities/unit';
import {UnitService} from '../../entities/unit';

@Component({
    selector: 'jhi-teachstate',
    templateUrl: './teachstate.component.html'
})
export class TeachstateComponent implements OnInit, OnDestroy {
    images: Image[];
    units: Unit[];
    unName: string;
    imgId:number;
    unId: number;
    condition: boolean=false;

    teachstate: Teachstate = new Teachstate();

    currentAccount: any;
    eventSubscriber: Subscription;
    isSaving: Boolean;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;
    itemsPerPage: any;
    page: any;
    predicate: any;
    previousPage: any;
    reverse: any;

    constructor(
        private imageService: ImageService,
        private unitService: UnitService,
        private teachstateService: TeachstateService,
        private parseLinks: JhiParseLinks,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.imageService.query().subscribe(
            (res: HttpResponse<Image[]>) => {
                this.images = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.unitService.query().subscribe(
            (res: HttpResponse<Unit[]>) => {
                this.units = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {

        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

        this.registerChangeInTeachstates();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }
    registerChangeInTeachstates() {
       this.eventSubscriber = this.eventManager.subscribe('teachstateListModification', (response) => this.loadAll());
    }

    sort() {
        const result = [this.predicate + ',' + (this.reverse ? 'asc' : 'desc')];
        if (this.predicate !== 'id') {
            result.push('id');
        }
        return result;
    }
    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
    onClickMe(uId:any) {
        this.unId=this.units[uId].id;
        this.unName=this.units[uId].unitName;

    }
}
